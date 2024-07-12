package com.gft.newmagicplatform.service;

import com.gft.newmagicplatform.entity.Account;

public interface CardService {

    public void addCard(Account account, String id);
    public void deleteCard(Account account, String id);

}
