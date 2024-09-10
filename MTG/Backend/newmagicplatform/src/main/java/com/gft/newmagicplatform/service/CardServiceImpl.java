package com.gft.newmagicplatform.service;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gft.newmagicplatform.entity.Account;
import com.gft.newmagicplatform.exception.UserNotFoundException;

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
    public boolean tradeCard(Account accountGiving, Account accountTaking, String cardId) throws UserNotFoundException {
        Account giver = accountService.getAccountById(accountGiving.getId());
        Account taker = accountService.getAccountById(accountTaking.getId());

        if (!giver.getCards().contains(cardId)) {
            return false;
        }

        deleteCardFromAccount(giver, cardId);
        addCardToAccount(taker, cardId);

        return true;
    }

    @Transactional
    @Override
    public void toggleSaleStatus(Account account, String id) {
        if (accountService.getAccountById(account.getId()).getCardsForSale().contains(id)) {
            accountService.getAccountById(account.getId()).getCardsForSale().remove(id);
        } else {
            accountService.getAccountById(account.getId()).getCardsForSale().add(id);
        }
    }

    @Override
    public Set<String> getCardsForSale(Account account) {
        return account.getCardsForSale();
    }

}
