package com.gft.newmagicplatform.service;

import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.gft.newmagicplatform.entity.Account;
import com.gft.newmagicplatform.io.JsonDataLoader;
import com.gft.newmagicplatform.pojo.Card;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CardServiceImpl implements CardService {

    private AccountService accountService;
    private JsonDataLoader io;

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
    public Card getCardById(String cardId) {
        return io.findCardByIdParrallel(cardId);
    }

    @Override
    public List<Card> getCardsByName(String name) {
        return io.findCardsByNameParralel(name);
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
