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
import java.util.Optional;

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
import com.gft.newmagicplatform.pojo.AccountDto;
import com.gft.newmagicplatform.service.AccountService;

import jakarta.persistence.EntityNotFoundException;

@RunWith(MockitoJUnitRunner.class)
public class AccountControllerTest {

    @Mock
    private AccountService accountService;

    @InjectMocks
    private AccountController accountController;

    private List<Account> mockAccounts;
    private Account mockAccount1;
    private Account mockAccount2;
    private AccountDto accountDto;

    @SuppressWarnings("unchecked")
    @Before
    public void setUp() throws ParseException {
        accountDto = new AccountDto("Firstname", "Lastname", "username", "password", "email", "2000-12-12");
        when(accountService.createAccount(accountDto)).thenReturn(
                new Account("Firstname", "Lastname", "username", "password", "2000-12-12", "email"));

        mockAccount1 = new Account("Chris", "Emerson", "breezy", "123", "1996-12-10", "cmen@gft.com");
        mockAccount1.setId(0L);
        mockAccount1.setCards(new HashSet<>());

        mockAccount2 = new Account("Test", "Test", "test", "123", "1996-12-10", "test@gft.com");
        mockAccount2.setId(1L);
        mockAccount2.setCards(new HashSet<>());

        mockAccounts = Arrays.asList(mockAccount1, mockAccount2);

        when(accountService.getAccounts()).thenReturn(mockAccounts);

        // Mocking the getById method to find accounts by a given id and return it
        Answer<Account> getById = new Answer<Account>() {
            @Override
            public Account answer(InvocationOnMock invocation) throws Throwable {
                long userId = invocation.getArgument(0);
                return mockAccounts.stream()
                        .filter(account -> account.getId() == userId)
                        .findFirst()
                        .orElseThrow(() -> new UserNotFoundException(userId));
            }
        };
        when(accountService.getAccountById(anyLong())).thenAnswer(getById);

        // Mocking the getByUsername method to find accounts by a given username and
        // return it
        Answer<Account> getByUsername = new Answer<Account>() {
            @Override
            public Account answer(InvocationOnMock invocation) throws Throwable {
                String username = invocation.getArgument(0);
                return mockAccounts.stream()
                        .filter(account -> account.getUsername().equals(username))
                        .findFirst()
                        .orElseThrow(() -> new Exception("No Account found with username: " + username));
            }
        };
        when(accountService.getAcountByUsername(anyString())).thenAnswer(getByUsername);

        // Mocking the updateAccount method to throw and exception if the account id
        // doesnt exist
        doAnswer(invocation -> {
            Long id = invocation.getArgument(0);
            Optional<Account> account = invocation.getArgument(1);

            // Simulate checking whether the account exists
            boolean accountExists = mockAccounts.stream()
                    .anyMatch(a -> a.getId().equals(id));

            // If account doesnt exist, throw UserNotFoundException
            if (!accountExists) {
                throw new EntityNotFoundException();
            }

            if (!account.isPresent()) {
                throw new EntityNotFoundException();
            }

            // Otherwise proceed as if the update was successful
            return null;

        }).when(accountService).updateAccount(anyLong(), any(Optional.class));

        when(accountService.accountExists(anyString())).thenAnswer(invocation -> {
            try {
                getByUsername.answer(invocation);
                return true;
            } catch (Exception e) {
                return false;
            }
        });

        when(accountService.emailExists(anyString())).thenAnswer(invocation -> {
            String email = invocation.getArgument(0);
            try {
                return mockAccounts.stream()
                        .filter(account -> account.getEmail().equals(email))
                        .findFirst()
                        .isPresent();
            } catch (Exception e) {
                return false;
            }
        });
    }

    @Test
    public void create_ShouldReturnAccount_WhenCreated() throws ParseException {
        String firstname = "Firstname";
        String lastname = "Lastname";
        String username = "username";
        String password = "password";
        String email = "email";
        String birthday = "2000-12-12";

        Account expectedAccount = new Account(firstname, lastname, username, password, birthday, email);

        assertEquals(expectedAccount,
                accountController.create(firstname, lastname, username, password, email, birthday));

        verify(accountService, times(1))
                .createAccount(new AccountDto(firstname, lastname, username, password, email, birthday));

    }

    @Test
    public void getById_ShouldReturnAccount_WhenAccountExists() {
        assertEquals(mockAccount1, accountController.getById(0L));
        verify(accountService, times(1)).getAccountById(0L);
    }

    @Test(expected = UserNotFoundException.class)
    public void getById_ShouldThrowException_WhenAccountNotFound() {
        accountController.getById(2L);
    }

    @Test
    public void getByUsername_ShouldReturnAccount_IfAccountExists() {
        assertEquals(mockAccount2, accountController.getByUsername("test"));
        verify(accountService, times(1)).getAcountByUsername("test");
    }

    @Test(expected = Exception.class)
    public void getByUsername_ShouldThrowException_WhenNoAccountByUsernameIsFound() {
        accountController.getByUsername("nonExistant");
    }

    @Test
    public void update_ShouldCallUpdateFunction_WhenInputtingCorrectIdAndAccountExists() {
        accountController.update(0L, Optional.of(mockAccount1));
        verify(accountService, times(1)).updateAccount(0L, Optional.of(mockAccount1));
    }

    @Test(expected = EntityNotFoundException.class)
    public void update_ShouldThrowException_WhenAcountByIdDoesntExist() {
        accountController.update(66L, Optional.of(mockAccount1));
    }

    @Test(expected = EntityNotFoundException.class)
    public void update_ShouldThrowException_WhenIdIsCorrectButAccountDoesntExist() {
        accountController.update(0L, Optional.empty());
    }

    @Test
    public void delete_ShouldCallDelete_WhenAccountByIdIsFound() {
        accountController.delete(0L);
        verify(accountService, times(1)).deleteAccount(0L);
    }

    @Test
    public void getAccount_ShouldReturnListOfAccounts() {
        assertEquals(mockAccounts, accountController.getAccounts());
        verify(accountService, times(1)).getAccounts();
    }

    @Test
    public void accountExists_ShouldReturnTrue_IfAccountExists() {
        assertTrue(accountController.accountExists("breezy"));
        verify(accountService, times(1)).accountExists("breezy");
    }

    @Test
    public void accountExists_ShouldReturnFalse_IfAccountDoesntExist() {
        assertFalse(accountController.accountExists("falseUsername"));
        verify(accountService, times(1)).accountExists("falseUsername");
    }

    @Test
    public void emailExists_ShouldReturnTrue_IfEmailExists() {
        assertTrue(accountController.emailExists("cmen@gft.com"));
        verify(accountService, times(1)).emailExists("cmen@gft.com");
    }

    @Test
    public void emailExists_ShouldReturnFalse_IfEmailDoesntExists() {
        assertFalse(accountController.emailExists("nonExistant@false.com"));
        verify(accountService, times(1)).emailExists("nonExistant@false.com");
    }

}
