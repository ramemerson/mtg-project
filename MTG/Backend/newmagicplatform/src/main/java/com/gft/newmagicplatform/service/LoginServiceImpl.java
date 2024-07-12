package com.gft.newmagicplatform.service;

import com.gft.newmagicplatform.entity.Account;
import com.gft.newmagicplatform.repository.AccountRepo;

public class LoginServiceImpl implements LoginService {

    AccountRepo accountRepo;
    AccountService accountService;

    @Override
    public boolean checkUsernamePassword(String username, String password) {
        for (Account account : accountService.getAccounts()) {
            if (username.equalsIgnoreCase(account.getUsername())) {
                if (password.equals(account.getPassword())) {
                    return true;
                }
                System.out.println("Username or Password wrong.");
            }
        }
        return false;
    }

}
