package ca.ulaval.glo4002.hibernate_example.interfaces.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import ca.ulaval.glo4002.hibernate_example.interfaces.AuthenticationForm;
import ca.ulaval.glo4002.hibernate_example.persistence.HibernateAccountRepository;
import ca.ulaval.glo4002.hibernate_example.services.AccountService;

@Path("/accounts")
@Produces(MediaType.APPLICATION_JSON)
public class AccountResource {

	private AccountService service;

	public AccountResource() {
		service = new AccountService(new HibernateAccountRepository());
	}

	@POST
	@Path("/validate")
	@Consumes(MediaType.APPLICATION_JSON)
	public Boolean validateCredentials(AuthenticationForm form) {
		return service.validateCredentials(form);
	}
}
