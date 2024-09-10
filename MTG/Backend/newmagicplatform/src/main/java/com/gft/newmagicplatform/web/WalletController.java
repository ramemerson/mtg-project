package com.gft.newmagicplatform.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.gft.newmagicplatform.service.WalletService;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/wallet")
@RestController("/wallet")
@CrossOrigin(origins = "http://localhost:4200")
@AllArgsConstructor
public class WalletController {

    private WalletService walletService;

    @GetMapping("/addBudget")
    public Double addBudgetToAccount(@RequestParam Double amountDouble, @RequestParam Long accId) {
        return walletService.addBudgetToAccount(accId, amountDouble);
    }

    @GetMapping("/subtractBudget")
    public Double subtractBudgetFromAccount(@RequestParam Double amountToSubtract, @RequestParam Long accId) {
        return walletService.subtractBudgetFromAccount(accId, amountToSubtract);
    }

}
