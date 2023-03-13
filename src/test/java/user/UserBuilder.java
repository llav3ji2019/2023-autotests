package user;

public interface UserBuilder {
    UserBuilder setLogin(String login);
    UserBuilder setPassword(String password);
    User build();
}
