package ca.ulaval.glo4002.hibernate_example.interfaces.rest;

import java.util.EnumSet;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.servlet.DispatcherType;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.servlet.ServletContainer;

import ca.ulaval.glo4002.hibernate_example.contexts.DemoAccountRepositoryFiller;
import ca.ulaval.glo4002.hibernate_example.persistence.EntityManagerFactoryProvider;
import ca.ulaval.glo4002.hibernate_example.persistence.HibernateAccountRepository;

public class RestMain {

	public static void main(String[] args) throws Exception {
		EntityManagerFactory entityManagerFactory = EntityManagerFactoryProvider.getFactory();
		EntityManager createEntityManager = entityManagerFactory.createEntityManager();
		HibernateAccountRepository hibernateAccountRepository = new HibernateAccountRepository(createEntityManager);
		new DemoAccountRepositoryFiller().fill(createEntityManager, hibernateAccountRepository);
		createEntityManager.close();

		new RestMain().execute();
	}

	public void execute() throws Exception {
		int httpPort = 8080;

		Server server = new Server(httpPort);
		ServletContextHandler servletContextHandler = new ServletContextHandler(server, "/");
		servletContextHandler.addFilter(EntityManagerContextFilter.class, "/*", EnumSet.of(DispatcherType.REQUEST));
		configurerJersey(servletContextHandler);
		server.start();
		server.join();
	}

	private void configurerJersey(ServletContextHandler servletContextHandler) {
		ServletContainer container = new ServletContainer(new ResourceConfig().packages(
				"ca.ulaval.glo4002.hibernate_example.interfaces.rest").register(JacksonFeature.class));
		ServletHolder jerseyServletHolder = new ServletHolder(container);
		servletContextHandler.addServlet(jerseyServletHolder, "/*");
	}
}
