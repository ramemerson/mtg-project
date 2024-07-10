package com.gft.newmagicplatform.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gft.newmagicplatform.entity.Account;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.GetMapping;


@Controller
@RequestMapping("/main")
public class HomePageController {
    
    @GetMapping("/")
    public String getMain(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("user") != null) {
            Account user = (Account) session.getAttribute("user");
            model.addAttribute("user", user);
            return "main";
        }
        return "redirect:/startup/";
    }
}
