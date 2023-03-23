package utils.user;

public interface UserBuilder {
    UserBuilder setLogin(String login);

    UserBuilder setPassword(String password);

    UserBuilder setName(String name);

    User build();
}
