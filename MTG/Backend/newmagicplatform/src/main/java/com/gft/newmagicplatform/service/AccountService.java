package com.gft.newmagicplatform.service;

import java.util.List;

import com.gft.newmagicplatform.entity.Account;

public interface AccountService {
    Account save(Account account);
    Account getAccountById(Long id);
    List<Account> getAccounts();
    boolean accountExists(Account account);
    String checkUsernamePassword(String username, String password);
    Account getAcountByUsername(String username);
    void addCard(Account account, String id);
    void deleteCard(Account account, String id);
}
