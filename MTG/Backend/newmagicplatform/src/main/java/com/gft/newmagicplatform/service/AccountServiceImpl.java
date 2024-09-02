package com.gft.newmagicplatform.service;

import java.text.ParseException;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import com.gft.newmagicplatform.entity.Account;
import com.gft.newmagicplatform.entity.Wallet;
import com.gft.newmagicplatform.exception.UserNotFoundException;
import com.gft.newmagicplatform.pojo.AccountDto;
import com.gft.newmagicplatform.repository.AccountRepo;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AccountServiceImpl implements AccountService {

    private AccountRepo accountRepo;

    @Override
    public Account save(Account account) {
        return accountRepo.save(account);
    }

    @Override
    public Account getAccountById(Long id) {
        Optional<Account> account = accountRepo.findById(id);
        return unwrapAccount(account, id);
    }

    @Override
    public List<Account> getAccounts() {
        return (List<Account>) accountRepo.findAll();
    }

    @Override
    public Account unwrapAccount(Optional<Account> entity, Long id) {
        if (entity.isPresent())
            return entity.get();
        throw new UserNotFoundException(id);
    }

    @Override
    public boolean accountExists(String username) {
        for (Account accountToSearch : getAccounts()) {
            if (accountToSearch.getUsername().equalsIgnoreCase(username)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Account getAcountByUsername(String username) {
        for (Account account : getAccounts()) {
            if (account.getUsername().equalsIgnoreCase(username)) {
                return account;
            }
        }
        throw new UserNotFoundException(username);
    }

    @Override
    public void deleteAccount(Long id) {
        accountRepo.deleteById(id);
    }

    @Override
    public void updateAccount(Long id, Optional<Account> userById) {
        Optional<Account> existingUser = accountRepo.findById(id);

        if (existingUser.isPresent() && userById.isPresent()) {

            Account oldAccount = existingUser.get();
            Account newAccount = userById.get();

            oldAccount.setFirstname(newAccount.getFirstname());
            oldAccount.setLastname(newAccount.getLastname());
            oldAccount.setEmail(newAccount.getEmail());
            oldAccount.setUsername(newAccount.getUsername());
            oldAccount.setPassword(newAccount.getPassword());
            oldAccount.setDecks(newAccount.getDecks());
            oldAccount.setWallet(newAccount.getWallet());
            accountRepo.save(oldAccount);
        } else {
            throw new EntityNotFoundException("Account with ID " + id + " was not found.");
        }
    }

    @Override
    public Account createAccount(AccountDto accountDto) {
        Account account = new Account();
        account.setFirstname(accountDto.getFirstname());
        account.setLastname(accountDto.getLastname());
        account.setUsername(accountDto.getUsername());
        account.setPassword(accountDto.getPassword());
        account.setEmail(accountDto.getEmail());
        try {
            account.setBirthday(accountDto.getBirthday());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Wallet wallet = new Wallet();
        wallet.setAccount(account);
        account.setWallet(wallet);

        return accountRepo.save(account);
    }

    @Override
    public boolean emailExists(String email) {
        return getAccounts().stream()
                .filter(account -> email.equalsIgnoreCase(account.getEmail()))
                .anyMatch(account -> true);
    }

}
