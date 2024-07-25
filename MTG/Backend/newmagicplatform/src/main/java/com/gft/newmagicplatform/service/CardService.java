package com.gft.newmagicplatform.service;

import java.util.Set;

import com.gft.newmagicplatform.entity.Account;

public interface CardService {

    public void addCardToAccount(Account account, String id);

    public void deleteCardFromAccount(Account account, String id);

    public Set<String> getAllCardsFromAccount(Account account);

    public boolean tradeCard(Account accountGiving, Account accountTaking, String cardId);

}
