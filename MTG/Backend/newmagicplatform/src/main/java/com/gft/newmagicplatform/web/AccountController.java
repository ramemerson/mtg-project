package com.gft.newmagicplatform.web;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.AllArgsConstructor;

import com.gft.newmagicplatform.entity.Account;
import com.gft.newmagicplatform.pojo.AccountDto;
import com.gft.newmagicplatform.service.AccountService;

@Controller
@AllArgsConstructor
@RequestMapping("/account")
@RestController("/account")
@CrossOrigin(origins = "http://localhost:4200")
public class AccountController {

    AccountService accountService;

    // @GetMapping("/")
    // public String getAccountPage(HttpServletRequest request, Model model) {
    // HttpSession session = request.getSession(false);

    // if (session != null && session.getAttribute("user") != null) {
    // Account user = (Account) session.getAttribute("user");
    // model.addAttribute("user", user);
    // return "accountpage";
    // }

    // return "redirect:/startup/";
    // }

    @PostMapping("/create")
    public Account create(@RequestBody AccountDto accountDto) {
        return accountService.createAccount(accountDto);
    }

    @GetMapping("/getById")
    public Account getById(@RequestParam Long id) {
        return accountService.getAccountById(id);
    }

    @GetMapping("/getByUsername")
    public Account getByUsername(@RequestParam String username) {
        return accountService.getAcountByUsername(username);
    }

    @PutMapping("/update/{id}")
    public void update(@RequestParam("id") Long id, @RequestBody Optional<Account> account) {
        accountService.updateAccount(id, account);
    }

    @DeleteMapping("/delete")
    public void delete(@RequestParam Long id) {
        accountService.deleteAccount(id);
    }

    @GetMapping("/getAccounts")
    public List<Account> getAccounts() {
        return accountService.getAccounts();
    }

    @GetMapping("/accountExists")
    public boolean accountExists(@RequestParam String username) {
        return accountService.accountExists(username);
    }
}
