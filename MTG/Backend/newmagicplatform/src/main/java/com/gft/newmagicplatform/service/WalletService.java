package com.gft.newmagicplatform.service;

public interface WalletService {

    Double addBudgetToAccount(Long id, Double amountToAdd);

    Double subtractBudgetFromAccount(Long id, Double amountToRemove);
}
