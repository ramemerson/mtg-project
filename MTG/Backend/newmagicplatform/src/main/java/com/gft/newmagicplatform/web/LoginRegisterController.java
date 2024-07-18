package com.gft.newmagicplatform.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gft.newmagicplatform.login.LoginRequest;
import com.gft.newmagicplatform.service.LoginService;

import lombok.AllArgsConstructor;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@RequestMapping("/login")
@RestController("/login")
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class LoginRegisterController {

    private LoginService loginService;

    @PostMapping("/attempt")
    public boolean loginAttempt(@RequestBody LoginRequest loginRequest) {
        return loginService.checkUsernamePassword(loginRequest.getUsername(), loginRequest.getPassword());
    }

}
