package com.gft.newmagicplatform.pojo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AccountDto {

    private String firstname;
    private String lastname;
    private String username;
    private String email;
    private String password;
    private String birthday;

    public AccountDto(String firstname, String lastname, String username, String password, String email,
            String birthday) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.username = username;
        this.email = email;
        this.password = password;
        this.birthday = birthday;
    }
}
