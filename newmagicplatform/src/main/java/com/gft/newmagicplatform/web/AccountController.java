package com.gft.newmagicplatform.web;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import com.gft.newmagicplatform.entity.Account;
import com.gft.newmagicplatform.service.AccountServiceImpl;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@AllArgsConstructor
@RequestMapping("/account")
public class AccountController {

    AccountServiceImpl accountServiceImpl;
    
    @GetMapping("/")
    public String getAccountPage(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession(false);

        if (session != null && session.getAttribute("user") != null) {
            Account user = (Account) session.getAttribute("user");
            model.addAttribute("user", user);
            return "accountpage";
        }

        return "redirect:/startup/";
    }

    @PostMapping("/savecard/{id}")
    public ResponseEntity<HttpStatus> saveCardToAccount(@PathVariable Long id, @RequestBody String cardId) {
        accountServiceImpl.addCard(accountServiceImpl.getAccountById(id), cardId);   
        
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/deletecard/{id}")
    public ResponseEntity<HttpStatus> deleteCardFromAccount(@PathVariable Long id, @RequestBody String cardId) {
        accountServiceImpl.deleteCard(accountServiceImpl.getAccountById(id), cardId);
        
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    
    


    

}
