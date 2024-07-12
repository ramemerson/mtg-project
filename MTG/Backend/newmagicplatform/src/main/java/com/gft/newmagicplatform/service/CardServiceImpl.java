package com.gft.newmagicplatform.service;

import org.springframework.stereotype.Service;

import com.gft.newmagicplatform.entity.Account;
import com.gft.newmagicplatform.repository.AccountRepo;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CardServiceImpl implements CardService {

    AccountRepo accountRepo;
    AccountService accountService;

    @Transactional
    @Override
    public void addCard(Account account, String id) {
        accountService.getAccountById(account.getId()).getCards().add(id);
    }

    @Transactional
    @Override
    public void deleteCard(Account account, String id) {
        accountService.getAccountById(account.getId()).getCards().remove(id);
    }

}
