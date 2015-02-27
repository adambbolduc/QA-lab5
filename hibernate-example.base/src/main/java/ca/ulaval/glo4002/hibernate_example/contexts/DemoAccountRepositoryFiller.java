package ca.ulaval.glo4002.hibernate_example.contexts;

import javax.persistence.EntityManager;

import ca.ulaval.glo4002.hibernate_example.domain.Account;
import ca.ulaval.glo4002.hibernate_example.domain.AccountRepository;

public class DemoAccountRepositoryFiller {

	public void fill(EntityManager entityManager, AccountRepository repository) {
		entityManager.getTransaction().begin();
		repository.persist(new Account("john", "doe"));
		repository.persist(new Account("jane", "doe"));
		entityManager.getTransaction().commit();
	}
}
