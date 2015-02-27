package ca.ulaval.glo4002.hibernate_example.integration;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import ca.ulaval.glo4002.hibernate_example.domain.Account;
import ca.ulaval.glo4002.hibernate_example.interfaces.AuthenticationForm;
import ca.ulaval.glo4002.hibernate_example.persistence.InMemoryAccountRepository;
import ca.ulaval.glo4002.hibernate_example.services.AccountService;

public class AccountServiceITest {

    private static final String USERNAME = "username";
    private static final String PASSWORD = "username";

    private InMemoryAccountRepository repository;
    private AccountService service;
    private Account account;

    @Before
    public void createServiceWithFilledInRepository() {
        createAndFillInRepository();
        service = new AccountService(repository);
    }

    @Test
    public void validateCredentialsValidatesWithExistingAccountAndGoodPassword() {
        boolean response = service.validateCredentials(createValidForm());

        assertTrue(response);
    }

    private AuthenticationForm createValidForm() {
        return new AuthenticationForm(USERNAME, PASSWORD);
    }

    private void createAndFillInRepository() {
        repository = new InMemoryAccountRepository();

        account = new Account(USERNAME, PASSWORD);
        repository.persist(account);
    }

}
