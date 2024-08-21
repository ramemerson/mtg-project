package com.gft.newmagicplatform.service;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.text.ParseException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;
import org.junit.runner.RunWith;

import com.gft.newmagicplatform.entity.Account;
import com.gft.newmagicplatform.exception.UserNotFoundException;

@RunWith(MockitoJUnitRunner.class)
public class CardServiceTest {

    @Mock
    private AccountService accountService;

    @InjectMocks
    private CardServiceImpl cardServiceImpl;

    private Account mockAccount1;
    private Account mockAccount2;
    private List<Account> mockAccounts;

    @Before
    public void setUp() throws ParseException {
        mockAccount1 = new Account("Chris", "Emerson", "breezy", "123", "1996-12-10", "cmen@gft.com");
        mockAccount1.setId(1L);
        mockAccount1.setCards(new HashSet<>());

        mockAccount2 = new Account("Chris", "Emerson", "breezy", "123", "1996-12-10", "cmen@gft.com");
        mockAccount2.setId(2L);
        mockAccount2.setCards(new HashSet<>());

        mockAccounts = Arrays.asList(mockAccount1, mockAccount2);

        Answer<Account> getAccountById = new Answer<Account>() {
            @Override
            public Account answer(InvocationOnMock invocation) throws Throwable {
                long userId = invocation.getArgument(0);
                return mockAccounts.stream()
                        .filter(account -> account.getId() == userId)
                        .findFirst()
                        .orElseThrow(() -> new UserNotFoundException(userId));
            }
        };
        when(accountService.getAccountById(anyLong())).thenAnswer(getAccountById);

    }

    @Test
    public void addCardToAccount_ShouldAddCard_WhenAccountAndCardIdAreGiven() {
        String cardId = "card123";
        cardServiceImpl.addCardToAccount(mockAccount1, cardId);
        verify(accountService, times(1)).getAccountById(mockAccount1.getId());
        assertTrue(mockAccount1.getCards().contains(cardId));
    }

    @Test(expected = UserNotFoundException.class)
    public void addCardToAccount_ShouldThrowException_IfAccountIsNotFound() {
        String cardId = "card123";
        Account account = new Account();
        account.setId(3L);
        cardServiceImpl.addCardToAccount(account, cardId);
    }

    @Test
    public void deleteCardFromAccount_ShouldDeleteCard_WhenCorrectAccountAndCardIdGiven() {
        String cardId = "card123";
        cardServiceImpl.deleteCardFromAccount(mockAccount1, cardId);
        verify(accountService, times(1)).getAccountById(mockAccount1.getId());
        assertFalse(mockAccount1.getCards().contains(cardId));
    }

    @Test(expected = UserNotFoundException.class)
    public void deleteCardFromAccount_ShouldThrowException_WhenAccountIsNotFound() {
        String cardId = "card123";
        Account account = new Account();
        account.setId(3L);
        cardServiceImpl.deleteCardFromAccount(account, cardId);
    }

    @Test
    public void getCards_ShouldGetAllCardsForAccount_IfAccountExists() {
        Set<String> cards = mockAccount1.getCards();
        assertEquals(cards, cardServiceImpl.getAllCardsFromAccount(mockAccount1));
    }

    @Test
    public void tradeCard_ShouldTradeCard_IfAccountsExist() {
        mockAccount1.getCards().add("card123");
        cardServiceImpl.tradeCard(mockAccount1, mockAccount2, "card123");
        assertTrue(mockAccount2.getCards().contains("card123"));
    }

    @Test(expected = UserNotFoundException.class)
    public void tradeCard_ShouldThrowException_IfAccountGivingCardDoesntExist() {
        Account account = new Account();
        account.setId(3L);
        cardServiceImpl.tradeCard(account, mockAccount1, "card123");
    }

    @Test(expected = UserNotFoundException.class)
    public void tradeCard_ShouldThrowException_IfAccountGettingCardDoesntExist() {
        mockAccount1.getCards().add("card123");
        Account account = new Account();
        account.setId(3L);
        cardServiceImpl.tradeCard(mockAccount1, account, "card123");
    }

    @Test
    public void tradeCard_ShouldReturnFalse_IfAccountDoesntHaveCardToTrade() {
        assertFalse(cardServiceImpl.tradeCard(mockAccount2, mockAccount1, "cardNonExistant"));
    }
}
