package com.gft.newmagicplatform.service;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gft.newmagicplatform.entity.Account;
import jakarta.transaction.Transactional;

@Service
public class CardServiceImpl implements CardService {

    @Autowired
    private AccountService accountService;

    @Transactional
    @Override
    public void addCardToAccount(Account account, String cardId) {
        accountService.getAccountById(account.getId()).getCards().add(cardId);
    }

    @Transactional
    @Override
    public void deleteCardFromAccount(Account account, String id) {
        accountService.getAccountById(account.getId()).getCards().remove(id);
    }

    @Override
    public Set<String> getAllCardsFromAccount(Account account) {
        return account.getCards();
    }

    @Override
    public boolean tradeCard(Account accountGiving, Account accountTaking, String cardId) {
        if (accountGiving.getCards().contains(cardId)) {
            deleteCardFromAccount(accountGiving, cardId);
            addCardToAccount(accountTaking, cardId);
            return true;
        }
        return false;
    }

}
