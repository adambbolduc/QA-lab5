package ca.ulaval.glo4002.hibernate_example.services;

import ca.ulaval.glo4002.hibernate_example.domain.Account;
import ca.ulaval.glo4002.hibernate_example.domain.AccountRepository;
import ca.ulaval.glo4002.hibernate_example.interfaces.AuthenticationForm;

public class AccountService {

    private AccountRepository repository;

    public AccountService(AccountRepository repository) {
        this.repository = repository;
    }

    public boolean validateCredentials(AuthenticationForm form) {
        Account account = repository.findByUsername(form.username);
        if (account == null) {
            return false;
        }

        return account.validatePassword(form.password);
    }

}
