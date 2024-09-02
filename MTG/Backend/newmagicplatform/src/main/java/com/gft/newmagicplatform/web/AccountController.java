package com.gft.newmagicplatform.web;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;

import lombok.AllArgsConstructor;

import com.gft.newmagicplatform.entity.Account;
import com.gft.newmagicplatform.exception.UserNotFoundException;
import com.gft.newmagicplatform.pojo.AccountDto;
import com.gft.newmagicplatform.service.AccountService;

@Controller
@AllArgsConstructor
@RequestMapping("/account")
@RestController("/account")
@CrossOrigin(origins = "http://localhost:4200")
public class AccountController {

    private AccountService accountService;

    @PostMapping("/create")
    public Account create(
            @RequestParam String firstname,
            @RequestParam String lastname,
            @RequestParam String username,
            @RequestParam String password,
            @RequestParam String email,
            @RequestParam String birthday) {
        return accountService.createAccount(new AccountDto(firstname, lastname, username, password, email, birthday));
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
    public void update(@PathVariable("id") String id, @RequestBody Account account) {
        Optional<Account> optionalAccount = Optional.ofNullable(account);
        accountService.updateAccount(Long.parseLong(id), optionalAccount);
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

    @GetMapping("/emailExists")
    public boolean emailExists(@RequestParam String email) {
        return accountService.emailExists(email);
    }

    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<String> handleNotFound(UserNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

}
