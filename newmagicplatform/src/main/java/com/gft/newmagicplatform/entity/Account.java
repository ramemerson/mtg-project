package com.gft.newmagicplatform.entity;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "account")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstname;

    private String lastname;

    @NonNull
    @NotBlank(message = "please enter a username")
    private String username;

    @NonNull
    @NotBlank(message = "please enter a password")
    private String password;

    private Date birthday;

    private String email;

    @OneToOne(mappedBy = "account", cascade = CascadeType.ALL)
    private Wallet wallet;

    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL)
    private Set<Deck> decks;

    @JsonIgnore
    @ElementCollection
    @CollectionTable(
        name = "account_cards", 
        joinColumns = @JoinColumn(name = "account_id"))
    @Column(name = "card_id")
    @Fetch(FetchMode.JOIN)
    private Set<String> cards;

    public Account(String firstName, String lastName, String username, String password, String birthday, String email) throws ParseException {
        setFirstname(firstName);
        setLastname(lastName);
        setUsername(username);
        setPassword(password);
        setBirthday(birthday);
        setEmail(email);
    }

    public void setBirthday(String date) throws ParseException {
        DateFormat sourceFormat = new SimpleDateFormat("yyyy-MM-dd");
        birthday = sourceFormat.parse(date);
    }

}
