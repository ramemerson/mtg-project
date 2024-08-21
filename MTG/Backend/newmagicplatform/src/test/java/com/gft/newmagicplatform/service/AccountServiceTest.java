package com.gft.newmagicplatform.service;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

import java.text.ParseException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;

import com.gft.newmagicplatform.entity.Account;
import com.gft.newmagicplatform.exception.UserNotFoundException;
import com.gft.newmagicplatform.pojo.AccountDto;
import com.gft.newmagicplatform.repository.AccountRepo;

import jakarta.persistence.EntityNotFoundException;

@RunWith(MockitoJUnitRunner.class)
public class AccountServiceTest {

    @Mock
    private AccountRepo accountRepo;

    @InjectMocks
    private AccountServiceImpl accountServiceImpl;

    private List<Account> mockAccounts;

    @Before
    public void setUp() throws ParseException {
        // Arrange
        mockAccounts = Arrays.asList(
                new Account("Chris", "Emerson", "breezy", "123", "1996-12-10", "cmen@gft.com"),
                new Account("Yasmin", "Hoffmann", "bagelz", "123", "1999-07-13", "yasmin.hoffmann@web.de"));

        when(accountRepo.findAll()).thenReturn(mockAccounts);

        Answer<Optional<Account>> findById = new Answer<Optional<Account>>() {
            @Override
            public Optional<Account> answer(InvocationOnMock invocation) throws Throwable {
                long userId = invocation.getArgument(0);
                try {
                    Account account = mockAccounts.get((int) userId);
                    return Optional.of(account);
                } catch (IndexOutOfBoundsException ex) {
                    return Optional.empty();
                }
            }
        };
        when(accountRepo.findById(anyLong())).thenAnswer(findById);

    }

    @Test
    public void getAccounts_AssertingIfAccountsAreInRepo() {
        // Act
        List<Account> result = accountServiceImpl.getAccounts();
        // Assert
        assertEquals(2, result.size());
        assertEquals("Chris", result.get(0).getFirstname());
        assertEquals("Hoffmann", result.get(1).getLastname());
        // Verify
        verify(accountRepo).findAll();
    }

    @Test
    public void accountExists_AssertingTrue_IfAccountExists() {
        assertTrue(accountServiceImpl.accountExists("breezy"));
        verify(accountRepo, times(1)).findAll();
    }

    @Test
    public void accountExists_AssertingFalse_IfAccountDoesntExist() {
        assertFalse(accountServiceImpl.accountExists("nonExistant"));
        verify(accountRepo, times(1)).findAll();
    }

    @Test
    public void accountExists_AssertingTrue_IfAcocuntExistsCaseInsensetive() {
        assertTrue(accountServiceImpl.accountExists("BREEZY"));
        verify(accountRepo, times(1)).findAll();
    }

    @Test
    public void accountExists_AssertingFalse_WhenUsernameIsEmpty() {
        assertFalse(accountServiceImpl.accountExists(""));
        verify(accountRepo).findAll();
    }

    @Test
    public void getAccountById_CheckingIfAccountIsFound() throws ParseException {
        Account account = mockAccounts.get(0);
        assertEquals(account, accountServiceImpl.getAccountById(0L));
    }

    @Test(expected = UserNotFoundException.class)
    public void getUnknownAccountById_ShouldThrowException() {
        accountServiceImpl.getAccountById(-1L);
    }

    @Test
    public void saveAccount_CheckingIfAccountIsSaved() throws ParseException {
        Account account = new Account("Bob", "OdenKirk", "bodenK", "123", "1996-12-10", "bodenk@gmail.com");
        accountServiceImpl.save(account);
        verify(accountRepo, times(1)).save(account);
    }

    @Test
    public void getAccountByUsername_ShouldReturnAccount_IfAccountExists() {
        assertEquals(mockAccounts.get(0), accountServiceImpl.getAcountByUsername("breezy"));
    }

    @Test
    public void getAccountByUsername_ShouldReturnNull_IfAccountDoesntExits() {
        assertNull(accountServiceImpl.getAcountByUsername("nonExistant"));
        verify(accountRepo, times(1)).findAll();
    }

    @Test
    public void deleteAccount_VerifyThatMethodIsBeingCalled() {
        accountServiceImpl.deleteAccount(2L);
        verify(accountRepo, times(1)).deleteById(2L);
    }

    @Test
    public void emailExists_ShouldReturnTrue_WithCorrectEmail() {
        assertTrue(accountServiceImpl.emailExists("cmen@gft.com"));
        verify(accountRepo, times(1)).findAll();
    }

    @Test
    public void emailExists_ShouldReturnFalse_IfEmailDoesntExist() {
        assertFalse(accountServiceImpl.emailExists("nonExistant@false.com"));
        verify(accountRepo, times(1)).findAll();
    }

    @Test(expected = EntityNotFoundException.class)
    public void updateAccount_ShouldThrowExcpetion_WithWrongIdButCorrectAccount() {
        accountServiceImpl.updateAccount(-1L, Optional.of(mockAccounts.get(0)));
        verify(accountRepo, times(1)).findById(0L);
    }

    @Test(expected = EntityNotFoundException.class)
    public void updateAccount_ShouldThrowException_WithCorrectIdButWrongAccount() {
        accountServiceImpl.updateAccount(0L, Optional.empty());
        verify(accountRepo, times(1)).findById(0L);
    }

    @Test
    public void updateAccount_ShouldUpdateAccount_WhenIdAndAccoutIsPresent() {
        accountServiceImpl.updateAccount(0L, Optional.of(mockAccounts.get(0)));
        verify(accountRepo, times(1)).save(mockAccounts.get(0));
    }

    @Test
    public void createAccount_ShouldSaveAccount_WhenAccountDtoIsGiven() {
        AccountDto accountDto = new AccountDto("Test", "Test", "Test", "123", "Test", "2000-12-12");
        accountServiceImpl.createAccount(accountDto);

        ArgumentCaptor<Account> accountCaptor = ArgumentCaptor.forClass(Account.class);
        verify(accountRepo, times(1)).save(accountCaptor.capture());

        Account savedAccount = accountCaptor.getValue();
        assertEquals("Test", savedAccount.getFirstname());
        assertEquals("Test", savedAccount.getLastname());
        assertEquals("Test", savedAccount.getUsername());
        assertEquals("123", savedAccount.getPassword());
        assertEquals("Test", savedAccount.getEmail());
        assertNotNull(savedAccount.getWallet());
        assertEquals(savedAccount, savedAccount.getWallet().getAccount());
    }

}
