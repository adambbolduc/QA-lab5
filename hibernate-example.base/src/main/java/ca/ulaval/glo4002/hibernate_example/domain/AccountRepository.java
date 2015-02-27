package ca.ulaval.glo4002.hibernate_example.domain;

public interface AccountRepository {

    Account findByUsername(String username);

    void persist(Account account);

}
