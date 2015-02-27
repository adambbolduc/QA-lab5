package ca.ulaval.glo4002.hibernate_example.persistence;

import java.util.HashMap;
import java.util.Map;

import ca.ulaval.glo4002.hibernate_example.domain.Account;
import ca.ulaval.glo4002.hibernate_example.domain.AccountRepository;

public class InMemoryAccountRepository implements AccountRepository {

    private Map<String, Account> accounts = new HashMap<>();

    @Override
    public Account findByUsername(String username) {
        return accounts.get(username);
    }

    public void persist(Account account) {
        accounts.put(account.getUsername(), account);
    }

}
