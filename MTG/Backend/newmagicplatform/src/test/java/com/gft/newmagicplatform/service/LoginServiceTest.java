package com.gft.newmagicplatform.service;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.text.ParseException;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.gft.newmagicplatform.entity.Account;

@RunWith(MockitoJUnitRunner.class)
public class LoginServiceTest {

    @Mock
    AccountService accountService;

    @InjectMocks
    LoginServiceImpl loginServiceImpl;

    List<Account> mockAccounts;

    @Before
    public void setUp() throws ParseException {
        mockAccounts = Arrays.asList(
                new Account("Chris", "Emerson", "breezy", "123", "1996-12-10", "cmen@gft.com"),
                new Account("Yasmin", "Hoffmann", "bagelz", "123", "1999-07-13", "yasmin.hoffmann@web.de"));
        when(accountService.getAccounts()).thenReturn(mockAccounts);
    }

    @Test
    public void checkUsernamePassword_ShouldReturnTrue_IfUsernameAndPasswordCorrect() {
        assertTrue(loginServiceImpl.checkUsernamePassword("breezy", "123"));
        verify(accountService).getAccounts();
    }

    @Test
    public void checkUsernamePassword_ShouldReturnFalse_IfUsernameCorrectPasswordWrong() {
        assertFalse(loginServiceImpl.checkUsernamePassword("breezy", "falsepassword"));
        verify(accountService).getAccounts();
    }

    @Test
    public void checkUsernamePassword_ShouldReturnFalse_IfUsernamewrongPasswordWrong() {
        assertFalse(loginServiceImpl.checkUsernamePassword("wrong", "wrong"));
        verify(accountService).getAccounts();
    }

}
