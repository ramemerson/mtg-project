package com.gft.newmagicplatform.service;

import org.springframework.stereotype.Service;

import com.gft.newmagicplatform.entity.Wallet;
import com.gft.newmagicplatform.repository.WalletRepo;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class WalletServiceImpl implements WalletService {

    private AccountService accountService;
    private WalletRepo walletRepo;

    @Override
    public Double addBudgetToAccount(Long id, Double amountToAdd) {
        Double budget = accountService.getAccountById(id).getWallet().getBudget() + amountToAdd;
        Wallet wallet = accountService.getAccountById(id).getWallet();
        wallet.setBudget(budget);
        walletRepo.save(wallet);
        return budget;
    }

    @Override
    public Double subtractBudgetFromAccount(Long id, Double amountToRemove) {
        Double budget = accountService.getAccountById(id).getWallet().getBudget() - amountToRemove;
        Wallet wallet = accountService.getAccountById(id).getWallet();
        wallet.setBudget(budget);
        walletRepo.save(wallet);
        return budget;
    }

}
