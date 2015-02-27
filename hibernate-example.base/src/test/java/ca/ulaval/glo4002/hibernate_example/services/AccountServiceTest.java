package ca.ulaval.glo4002.hibernate_example.services;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.BDDMockito.willReturn;
import static org.mockito.Mockito.verify;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import ca.ulaval.glo4002.hibernate_example.domain.Account;
import ca.ulaval.glo4002.hibernate_example.domain.AccountRepository;
import ca.ulaval.glo4002.hibernate_example.interfaces.AuthenticationForm;

@RunWith(MockitoJUnitRunner.class)
public class AccountServiceTest {

    private final static String USERNAME = "username";
    private final static String PASSWORD = "password";

    @Mock
    private Account account;

    @Mock
    private AccountRepository accountRepository;

    @InjectMocks
    private AccountService service;

    @Test
    public void validateCredentialsFindsAccountInRepository() {
        AuthenticationForm form = new AuthenticationForm(USERNAME, PASSWORD);

        service.validateCredentials(form);

        verify(accountRepository).findByUsername(USERNAME);
    }

    @Test
    public void validateCredentialsDoesNotValidateIfAccountIsNotFound() {
        willReturn(null).given(accountRepository).findByUsername(USERNAME);
        AuthenticationForm form = new AuthenticationForm(USERNAME, PASSWORD);

        boolean response = service.validateCredentials(form);

        assertFalse(response);
    }

    @Test
    public void validateCredentialsValidatesPasswordWithAccountFound() {
        willReturn(account).given(accountRepository).findByUsername(USERNAME);
        AuthenticationForm form = new AuthenticationForm(USERNAME, PASSWORD);

        service.validateCredentials(form);

        verify(account).validatePassword(PASSWORD);
    }

    @Test
    public void validateCredentialsValidatesFormIfAccountExistsAndPasswordIsValid() {
        willReturn(account).given(accountRepository).findByUsername(USERNAME);
        willReturn(true).given(account).validatePassword(PASSWORD);
        AuthenticationForm form = new AuthenticationForm(USERNAME, PASSWORD);

        boolean response = service.validateCredentials(form);

        assertTrue(response);
    }
}
