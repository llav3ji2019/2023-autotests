package utils.user;

public class User {
    private final String fullName;
    private final String login;
    private final String password;

    public User(final String fullName, final String login, final String password) {
        this.fullName = fullName;
        this.login = login;
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getFullName() {
        return fullName;
    }
}
