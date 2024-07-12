package com.gft.newmagicplatform.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gft.newmagicplatform.entity.Account;
import com.gft.newmagicplatform.entity.Wallet;
import com.gft.newmagicplatform.login.LoginResponse;
import com.gft.newmagicplatform.service.AccountServiceImpl;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/startup")
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class LoginRegisterController {

    private AccountServiceImpl accountServiceImpl;

    @GetMapping("/")
    public String getLoginOrRegisterPage() {
        return "registerorlogin";
    }

    @PostMapping("login")
    public String getLoginForm(Model model) {
        model.addAttribute("account", new Account());
        return "login";
    }

    @PostMapping("login/attempt")
    public String loginAttempt(Account account, RedirectAttributes ra, HttpServletRequest request) {
        String status = "failed";
        if (accountServiceImpl.checkUsernamePassword(account.getUsername(), account.getPassword())
                .equals("usernameAndPasswordCorrect")) {
            status = "success";
            HttpSession session = request.getSession();
            session.setAttribute("user", accountServiceImpl.getAcountByUsername(account.getUsername()));
            ra.addFlashAttribute("status", status);
            return "redirect:/main/";
        } else if (accountServiceImpl.checkUsernamePassword(account.getUsername(), account.getPassword())
                .equals("usernameCorrectPasswordWrong")) {
            status = "nameOrPasswordWrong";
            ra.addFlashAttribute("status", status);
            return "redirect:/startup/";
        }
        ra.addFlashAttribute("status", status);
        return "redirect:/startup/";
    }

    @GetMapping("/loginattempt")
    public LoginResponse authenticate(String username, String password) {
        
        return new LoginResponse();

    }

    @PostMapping("register")
    public String getRegisterPage(Model model) {
        model.addAttribute("account", new Account());
        return "register";
    }

    @PostMapping("register/attempt")
    public String registerAttempt(Account account, RedirectAttributes ra) {
        String status = "failed";
        if (accountServiceImpl.accountExists(account)) {
            status = "userexists";
            ra.addFlashAttribute("status", status);
            return "redirect:/startup/";
        }
        Wallet wallet = new Wallet();
        wallet.setAccount(account);
        account.setWallet(wallet);
        accountServiceImpl.save(account);
        status = "successful";
        ra.addFlashAttribute("status", status);
        return "redirect:/main/";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        return "redirect:/startup/";
    }

}
