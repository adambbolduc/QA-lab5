package ca.ulaval.glo4002.hibernate_example.domain;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Account {

	@Id
	private String username;
	private String password;

	public Account(String username, String password) {
		this.username = username;
		this.password = password;
	}

	public Account() {

	}

	public String getUsername() {
		return username;
	}

	public boolean validatePassword(String passwordToValidate) {
		return password.equals(passwordToValidate);
	}

}
