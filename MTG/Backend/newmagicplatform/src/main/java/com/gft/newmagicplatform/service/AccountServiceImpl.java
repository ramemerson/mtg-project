package com.gft.newmagicplatform.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.gft.newmagicplatform.entity.Account;
import com.gft.newmagicplatform.exception.UserNotFoundException;
import com.gft.newmagicplatform.repository.AccountRepo;

import jakarta.transaction.Transactional;
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

    static Account unwrapAccount(Optional<Account> entity, Long id) {
        if (entity.isPresent()) return entity.get();
        else throw new UserNotFoundException(id);
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

    @Transactional
    @Override
    public void addCard(Account account, String id) {
        getAccountById(account.getId()).getCards().add(id);
    }

    @Transactional
    @Override
    public void deleteCard(Account account, String id) {
        getAccountById(account.getId()).getCards().remove(id);
    }

    
    

}
