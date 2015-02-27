package ca.ulaval.glo4002.hibernate_example.interfaces.console;

import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import ca.ulaval.glo4002.hibernate_example.contexts.DemoAccountRepositoryFiller;
import ca.ulaval.glo4002.hibernate_example.domain.AccountRepository;
import ca.ulaval.glo4002.hibernate_example.interfaces.AuthenticationForm;
import ca.ulaval.glo4002.hibernate_example.persistence.EntityManagerFactoryProvider;
import ca.ulaval.glo4002.hibernate_example.persistence.EntityManagerProvider;
import ca.ulaval.glo4002.hibernate_example.persistence.HibernateAccountRepository;
import ca.ulaval.glo4002.hibernate_example.services.AccountService;

public class ConsoleMain {

	public static void main(String[] args) {
		EntityManagerFactory entityManagerFactory = EntityManagerFactoryProvider.getFactory();
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		EntityManagerProvider.setEntityManager(entityManager);
		AccountRepository repository = createDemoAccountRepository(entityManager);

		new ConsoleMain(repository).execute();

		EntityManagerProvider.clearEntityManager();
		entityManager.close();
		entityManagerFactory.close();
	}

	private static AccountRepository createDemoAccountRepository(EntityManager entityManager) {
		AccountRepository repository = new HibernateAccountRepository();
		new DemoAccountRepositoryFiller().fill(entityManager, repository);
		return repository;
	}

	private AccountService accountService;

	public ConsoleMain(AccountRepository repository) {
		accountService = new AccountService(repository);
	}

	private void execute() {
		AuthenticationForm form = fillInAuthenticationForm();
		boolean isValid = accountService.validateCredentials(form);

		System.out.println("Are your credentials valid? " + isValid);
	}

	private AuthenticationForm fillInAuthenticationForm() {
		Scanner console = new Scanner(System.in);

		System.out.println("Username: ");
		String username = console.next();
		System.out.println("Password: ");
		String password = console.next();

		console.close();

		AuthenticationForm form = new AuthenticationForm(username, password);
		return form;
	}

}
