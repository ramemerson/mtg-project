package com.gft.newmagicplatform.service;

import org.springframework.stereotype.Service;

import com.gft.newmagicplatform.entity.Deck;
import com.gft.newmagicplatform.repository.DeckRepo;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class DeckServiceImpl implements DeckService {

    private DeckRepo deckRepo;

    @Override
    public Deck save(Deck deck) {
        return deckRepo.save(deck);
    }

}
