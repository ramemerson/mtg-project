package com.gft.newmagicplatform.web;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doAnswer;
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
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;

import com.gft.newmagicplatform.entity.Account;
import com.gft.newmagicplatform.exception.UserNotFoundException;
import com.gft.newmagicplatform.service.AccountService;
import com.gft.newmagicplatform.service.CardService;

@RunWith(MockitoJUnitRunner.class)
public class CardControllerTest {

    @Mock
    private CardService cardService;

    @Mock
    private AccountService accountService;

    @InjectMocks
    private CardController cardController;

    private List<Account> mockAccounts;
    private Account mockAccount1;
    private Account mockAccount2;

    @Before
    public void setUp() throws ParseException {

        mockAccount1 = new Account("Chris", "Emerson", "breezy", "123", "1996-12-10", "cmen@gft.com");
        mockAccount1.setId(0L);
        mockAccount1.setCards(new HashSet<>());

        mockAccount2 = new Account("Test", "Test", "test", "123", "1996-12-10", "test@gft.com");
        mockAccount2.setId(1L);
        Set<String> cards = new HashSet<>();
        cards.add("123");
        cards.add("335");
        cards.add("324");
        mockAccount2.setCards(cards);

        mockAccounts = Arrays.asList(mockAccount1, mockAccount2);

        Answer<Account> getAccountById = new Answer<Account>() {
            @Override
            public Account answer(InvocationOnMock invocation) throws Throwable {
                Long id = invocation.getArgument(0);
                return mockAccounts.stream()
                        .filter(acc -> acc.getId().equals(id))
                        .findFirst()
                        .orElseThrow(() -> new UserNotFoundException(id));
            }
        };
        when(accountService.getAccountById(anyLong())).thenAnswer(getAccountById);

        doAnswer(new Answer<Void>() {
            @Override
            public Void answer(InvocationOnMock invocation) throws Throwable {
                Account account = invocation.getArgument(0);
                String id = invocation.getArgument(1);

                // Modify the account by adding the card ID
                account.getCards().add(id);

                // Return null because the method returns void
                return null;
            }
        }).when(cardService).addCardToAccount(any(Account.class), anyString());

        doAnswer(new Answer<Void>() {
            @Override
            public Void answer(InvocationOnMock invocation) throws Throwable {
                String cardId = invocation.getArgument(1);
                Account account = invocation.getArgument(0);

                if (account.getCards().contains(cardId)) {
                    account.getCards().remove(cardId);
                }

                return null;
            }
        }).when(cardService).deleteCardFromAccount(any(Account.class), anyString());

        Answer<Set<String>> getCardsFromAccount = new Answer<Set<String>>() {
            @Override
            public Set<String> answer(InvocationOnMock invocation) throws Throwable {
                Account account = invocation.getArgument(0);
                if (!account.getCards().isEmpty()) {
                    return account.getCards();
                }
                return null;
            }
        };
        when(cardService.getAllCardsFromAccount(any(Account.class))).thenAnswer(getCardsFromAccount);
    }

    @Test
    public void saveCardToAccount_ShouldAddCardToSet_IfAccountExists() {
        Account account = accountService.getAccountById(1L);
        String card = "123";
        cardController.saveCardToAccount(card, account.getId());
        verify(cardService, times(1)).addCardToAccount(account, card);
        assertTrue(account.getCards().contains(card));
    }

    @Test(expected = UserNotFoundException.class)
    public void saveCardToAccount_ShouldThrowException_WhenAccountNotFound() {
        String card = "123card";
        Account account = accountService.getAccountById(3L);
        cardController.saveCardToAccount(card, account.getId());
        verify(accountService, times(1)).getAccountById(3L);
    }

    @Test
    public void deleteCardFromAccount_ShouldDeleteCard_IfAccountExistsAndCardIsPresentInAccount() {
        String card = "card";
        accountService.getAccountById(1L).getCards().add(card);
        assertTrue(accountService.getAccountById(1L).getCards().contains(card));
        cardController.deleteCardFromAccount(card, accountService.getAccountById(1L).getId());
        verify(cardService, times(1)).deleteCardFromAccount(mockAccount2, card);
        assertFalse(accountService.getAccountById(1L).getCards().contains(card));
    }

    @Test(expected = UserNotFoundException.class)
    public void deleteCardFromAccount_ShouldThrowException_IfAccountNotFound() {
        String card = "card";
        cardController.deleteCardFromAccount(card, accountService.getAccountById(3L).getId());
        verify(cardService, times(1)).deleteCardFromAccount(accountService.getAccountById(3L), card);
    }

    @Test
    public void getCardsFromAccount_ShouldReturnCards_WhenAccountExists() {
        Set<String> cards = cardController.getCardsFromAccount(accountService.getAccountById(1L).getId());
        assertEquals(cards, mockAccount2.getCards());
        verify(accountService, times(2)).getAccountById(1L);
    }

    @Test(expected = UserNotFoundException.class)
    public void getCardsFromAccount_ShouldThrowException_WhenAccountNotFound() {
        Set<String> cards = cardController.getCardsFromAccount(accountService.getAccountById(5L).getId());
        verify(accountService, times(1)).getAccountById(5L);
    }

}
