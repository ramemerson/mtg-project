package com.gft.newmagicplatform.web;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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

    @PostMapping("/save/{id}")
    public ResponseEntity<HttpStatus> saveCardToAccount(@PathVariable Long id, @RequestBody String cardId) {
        cardService.addCard(accountService.getAccountById(id), cardId);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<HttpStatus> deleteCardFromAccount(@PathVariable Long id, @RequestBody String cardId) {
        cardService.deleteCard(accountService.getAccountById(id), cardId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
