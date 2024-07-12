package com.gft.newmagicplatform.service;

import java.text.ParseException;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import com.gft.newmagicplatform.entity.Account;
import com.gft.newmagicplatform.exception.UserNotFoundException;
import com.gft.newmagicplatform.repository.AccountRepo;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AccountServiceImpl implements AccountService {

    AccountRepo accountRepo;

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
    public boolean accountExists(Account account) {
        for (Account account2 : getAccounts()) {
            if (account2.getUsername().equalsIgnoreCase(account.getUsername())) {
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
        return null;
    }

    @Override
    public void deleteAccount(Long id) {
        accountRepo.deleteById(id);
    }

    @Override
    public Account updateAccount(Long id, Account account) {
        Optional<Account> accountFound = accountRepo.findById(id);
        if (accountFound.isPresent()) {
            Account accountToUpdate = accountFound.get();
            accountToUpdate.setFirstname(account.getFirstname());
            accountToUpdate.setLastname(account.getLastname());
            accountToUpdate.setUsername(account.getUsername());
            accountToUpdate.setPassword(account.getPassword());
            try {
                accountToUpdate.setBirthday(account.getBirthdayStringFormatted());
            } catch (ParseException e) {
                e.printStackTrace();
            }
            accountToUpdate.setEmail(account.getEmail());
            return accountRepo.save(accountToUpdate);
        }
        return null;
    }

}
