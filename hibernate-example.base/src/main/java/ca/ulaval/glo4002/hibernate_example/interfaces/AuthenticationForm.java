package ca.ulaval.glo4002.hibernate_example.interfaces;

public class AuthenticationForm {

    public AuthenticationForm(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public final String username;
    public final String password;

}
