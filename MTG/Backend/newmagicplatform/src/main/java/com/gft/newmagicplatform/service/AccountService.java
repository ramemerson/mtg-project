package com.gft.newmagicplatform.service;

import java.util.List;
import java.util.Optional;

import com.gft.newmagicplatform.entity.Account;
import com.gft.newmagicplatform.pojo.AccountDto;

public interface AccountService {
    Account save(Account account);

    Account getAccountById(Long id);

    List<Account> getAccounts();

    boolean accountExists(String username);

    Account getAcountByUsername(String username);

    void deleteAccount(Long id);

    void updateAccount(Long id, Optional<Account> userById);

    Account unwrapAccount(Optional<Account> entity, Long id);

    Account createAccount(AccountDto accountDto);

}
