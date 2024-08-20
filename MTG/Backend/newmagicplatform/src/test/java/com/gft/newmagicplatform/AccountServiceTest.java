package com.gft.newmagicplatform;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.text.ParseException;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.gft.newmagicplatform.entity.Account;
import com.gft.newmagicplatform.repository.AccountRepo;
import com.gft.newmagicplatform.service.AccountServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class AccountServiceTest {

    @Mock
    private AccountRepo accountRepo;

    @InjectMocks
    private AccountServiceImpl accountService;

    @Test
    public void getAccountsFromRepoTest() throws ParseException {

        // Arrange
        List<Account> mockAccounts = Arrays.asList(
                new Account("Chris", "Emerson", "breezy", "123", "1996-12-10", "cr.emerson@me.com"),
                new Account("Yasmin", "Hoffmann", "bagelz", "123", "1999-07-13", "yasmin.hoffmann@web.de"));
        when(accountRepo.findAll()).thenReturn(mockAccounts);

        // Act
        List<Account> result = accountService.getAccounts();

        // Assert
        assertEquals(2, result.size());
        assertEquals("Chris", result.get(0).getFirstname());
        assertEquals("Hoffmann", result.get(1).getLastname());

        // Verify
        verify(accountRepo).findAll();
    }

}
