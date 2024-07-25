package com.gft.newmagicplatform.web;

import java.util.Set;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gft.newmagicplatform.service.AccountService;
import com.gft.newmagicplatform.service.CardService;

@Controller
@RequestMapping("/card")
@RestController("/card")
@CrossOrigin(origins = "http://localhost:4200")
public class CardController {

    CardService cardService;
    AccountService accountService;

    @GetMapping("/save")
    public void saveCardToAccount(@RequestParam String cardId, @RequestParam Long accId) {
        cardService.addCardToAccount(accountService.getAccountById(accId), cardId);
    }

    @DeleteMapping("/delete")
    public void deleteCardFromAccount(@RequestParam String cardId, @RequestParam Long accId) {
        cardService.deleteCardFromAccount(accountService.getAccountById(accId), cardId);
    }

    @GetMapping("/getCardsFromAccount")
    public Set<String> getCardsFromAccount(@RequestParam Long accId) {
        return cardService.getAllCardsFromAccount(accountService.getAccountById(accId));
    }

}
