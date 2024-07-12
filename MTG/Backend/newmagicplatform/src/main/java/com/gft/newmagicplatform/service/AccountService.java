package com.gft.newmagicplatform.service;

import java.util.List;
import java.util.Optional;

import com.gft.newmagicplatform.entity.Account;

public interface AccountService {
    Account save(Account account);

    Account getAccountById(Long id);

    List<Account> getAccounts();

    boolean accountExists(Account account);

    Account getAcountByUsername(String username);

    void deleteAccount(Long id);

    Account updateAccount(Long id, Account account);

    Account unwrapAccount(Optional<Account> entity, Long id);
}
