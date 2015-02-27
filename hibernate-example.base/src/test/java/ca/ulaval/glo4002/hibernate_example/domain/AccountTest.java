package ca.ulaval.glo4002.hibernate_example.domain;

import static org.junit.Assert.*;

import org.junit.Test;

public class AccountTest {

    private static final String USERNAME = "username";

    @Test
    public void validatePasswordsValidatesWithGoodPassword() {
        String password = "password";
        Account account = new Account(USERNAME, password);

        boolean response = account.validatePassword(password);

        assertTrue(response);
    }

    @Test
    public void validatePasswordsDoesNotValidatesWithBadPassword() {
        String password = "password";
        String wrongPassword = "wrong";
        Account account = new Account(USERNAME, password);

        boolean response = account.validatePassword(wrongPassword);

        assertFalse(response);
    }

}
