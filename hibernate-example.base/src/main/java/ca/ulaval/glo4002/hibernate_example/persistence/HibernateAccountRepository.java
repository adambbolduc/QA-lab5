package ca.ulaval.glo4002.hibernate_example.persistence;

import javax.persistence.EntityManager;

import ca.ulaval.glo4002.hibernate_example.domain.Account;
import ca.ulaval.glo4002.hibernate_example.domain.AccountRepository;

public class HibernateAccountRepository implements AccountRepository {

	private EntityManager entityManager;

	public HibernateAccountRepository() {
		entityManager = new EntityManagerProvider().getEntityManager();
	}

	public HibernateAccountRepository(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	public Account findByUsername(String username) {
		return entityManager.find(Account.class, username);
	}

	@Override
	public void persist(Account account) {
		entityManager.persist(account);
	}

}
