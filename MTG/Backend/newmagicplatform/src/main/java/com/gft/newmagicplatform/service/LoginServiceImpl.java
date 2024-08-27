package com.gft.newmagicplatform.service;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class LoginServiceImpl implements LoginService {

    private AccountService accountService;

    @Override
    public boolean checkUsernamePassword(String username, String password) {
        return accountService.getAccounts().stream()
                .filter(account -> username.equalsIgnoreCase(account.getUsername()))
                .anyMatch(account -> password.equals(account.getPassword()));
    }

}
