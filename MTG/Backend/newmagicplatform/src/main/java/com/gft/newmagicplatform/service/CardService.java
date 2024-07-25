package com.gft.newmagicplatform.service;

import java.util.List;
import java.util.Set;

import com.gft.newmagicplatform.entity.Account;
import com.gft.newmagicplatform.pojo.Card;

public interface CardService {

    public void addCardToAccount(Account account, String id);

    public void deleteCardFromAccount(Account account, String id);

    public Set<Card> getAllCardsFromAccount(Account account);

    public Card getCardById(String cardId);

    public List<Card> getCardsByName(String name);

    public boolean tradeCard(Account accountGiving, Account accountTaking, String cardId);

    public Set<

}
