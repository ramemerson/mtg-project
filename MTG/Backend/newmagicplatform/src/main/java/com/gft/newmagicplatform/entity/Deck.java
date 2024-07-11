package com.gft.newmagicplatform.entity;

import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Table(name = "deck")
public class Deck {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int amountOfCards;
    private String deckArchytype;
    private String saleStatus;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;

    @JsonIgnore
    @ElementCollection
    @CollectionTable(
        name = "deck_cards", 
        joinColumns = @JoinColumn(name = "deck_id")
    )
    @Column(name = "card_id")
    private List<String> cards; 

    public Deck(int amountOfCards, String deckArchytype, String saleStatus) {
        setAmountOfCards(amountOfCards);
        setDeckArchytype(deckArchytype);
        setSaleStatus(saleStatus);
        cards = new ArrayList<>();
    }

}
