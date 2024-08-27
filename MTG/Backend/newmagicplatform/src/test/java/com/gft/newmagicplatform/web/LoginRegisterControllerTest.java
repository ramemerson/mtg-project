package com.gft.newmagicplatform.web;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
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
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;

import com.gft.newmagicplatform.entity.Account;
import com.gft.newmagicplatform.login.LoginRequest;
import com.gft.newmagicplatform.service.LoginService;

@RunWith(MockitoJUnitRunner.class)
public class LoginRegisterControllerTest {

    @Mock
    private LoginService loginService;

    @InjectMocks
    private LoginRegisterController loginRegisterController;

    private LoginRequest loginRequest;
    private LoginRequest loginRequest1;
    private List<Account> mockAccounts;
    private Account mockAccount1;
    private Account mockAccount2;

    @Before
    public void setUp() throws ParseException {

        mockAccount1 = new Account("Chris", "Emerson", "breezy", "123", "1996-12-10", "cmen@gft.com");
        mockAccount2 = new Account("Test", "Test", "test", "123", "1996-12-10", "test@gft.com");
        loginRequest = new LoginRequest();
        loginRequest.setUsername("breezy");
        loginRequest.setPassword("123");

        loginRequest1 = new LoginRequest();
        loginRequest1.setUsername("asdf");
        loginRequest1.setPassword("asdf");
        mockAccounts = Arrays.asList(mockAccount1, mockAccount2);

        Answer<Boolean> checkUsernamePassword = new Answer<Boolean>() {
            @Override
            public Boolean answer(InvocationOnMock invocation) throws Throwable {
                String username = invocation.getArgument(0);
                String password = invocation.getArgument(1);

                return mockAccounts.stream()
                        .anyMatch(acc -> acc.getUsername().equals(username) && acc.getPassword().equals(password));
            }
        };

        when(loginService.checkUsernamePassword(anyString(), anyString())).thenAnswer(checkUsernamePassword);
    }

    @Test
    public void checkUsernamePassword_ShouldReturnTrue_IfUsernameAndPasswordAreCorrect() {
        assertTrue(loginRegisterController.loginAttempt(loginRequest));
        verify(loginService, times(1)).checkUsernamePassword("breezy", "123");
    }

    @Test
    public void checkUsernamePassword_ShouldReturnFalse_IfUsernameIsWrongButPassword() {
        assertFalse(loginRegisterController.loginAttempt(loginRequest1));
        verify(loginService, times(1)).checkUsernamePassword("asdf", "asdf");
    }
}
