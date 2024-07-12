package com.gft.newmagicplatform.web;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;

import com.gft.newmagicplatform.entity.Account;
import com.gft.newmagicplatform.service.AccountService;
import com.gft.newmagicplatform.service.AccountServiceImpl;

@Controller
@AllArgsConstructor
@RequestMapping("/account")
@RestController("/account")
@CrossOrigin(origins = "http://localhost:4200")
public class AccountController {

    AccountService accountService;

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

    @GetMapping("/getById")
    public Optional<Account> getById(@RequestParam Long id) {
        return accountService.getAccountById(id);
    }

}
