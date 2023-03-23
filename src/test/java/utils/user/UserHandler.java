package utils.user;

public class UserHandler implements UserBuilder {
    private String name;
    private String login;
    private String password;

    @Override
    public UserBuilder setName(String name) {
        this.name = name;
        return this;
    }

    @Override
    public UserBuilder setLogin(String login) {
        this.login = login;
        return this;
    }

    @Override
    public UserBuilder setPassword(String password) {
        this.password = password;
        return this;
    }

    @Override
    public User build() {
        return new User(name, login, password);
    }
}
