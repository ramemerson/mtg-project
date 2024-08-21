package com.gft.newmagicplatform.service;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.gft.newmagicplatform.entity.Deck;
import com.gft.newmagicplatform.repository.DeckRepo;

@RunWith(MockitoJUnitRunner.class)
public class DeckServiceTest {

    @Mock
    DeckRepo deckRepo;

    @InjectMocks
    DeckServiceImpl deckServiceImpl;

    @Test
    public void save_ShouldSaveDeck() {
        Deck deck = new Deck(10, "AGGRO", "FOR_SALE");
        deckServiceImpl.save(deck);
        verify(deckRepo, times(1)).save(deck);
    }

}
