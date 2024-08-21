package com.gft.newmagicplatform.pojo;

import org.apache.commons.lang3.builder.EqualsBuilder;

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

    public boolean equals(Object other) {
        if (!getClass().equals(other.getClass())) {
            return false;
        }
        AccountDto otherAccount = (AccountDto) other;
        EqualsBuilder equalsBuilder = new EqualsBuilder();
        equalsBuilder.append(firstname, otherAccount.firstname);
        equalsBuilder.append(lastname, otherAccount.lastname);
        equalsBuilder.append(username, otherAccount.username);
        equalsBuilder.append(email, otherAccount.email);
        equalsBuilder.append(password, otherAccount.password);
        equalsBuilder.append(birthday, otherAccount.birthday);
        return equalsBuilder.build();
    }
}
