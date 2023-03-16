package page.login;

import org.jetbrains.annotations.NotNull;

import com.codeborne.selenide.SelenideElement;

import page.LoadableComponent;
import page.home.HomePage;
import user.User;
import user.UserHandler;

import static com.codeborne.selenide.Selenide.$x;

public class LoginPage implements LoadableComponent {
    private static final long TIME_OUT_IN_SECONDS = 5;
    private final SelenideElement loginInput = $x("//input[@id='field_email']");
    private final SelenideElement passwordInput = $x("//input[@id='field_password']");
    private final SelenideElement signInButton = $x("//input[@type='submit' and @class='button-pro __wide']");

    public HomePage signIn(@NotNull final User user) {
        return doSignIn(user);
    }

    public HomePage signIn(@NotNull final String name, @NotNull final String login, @NotNull final String password) {
        return signIn(createUser(name, login, password));
    }

    private HomePage doSignIn(@NotNull final User user) {
        isLoaded(loginInput, TIME_OUT_IN_SECONDS).setValue(user.getLogin());
        isLoaded(passwordInput, TIME_OUT_IN_SECONDS).setValue(user.getPassword());
        isLoaded(signInButton, TIME_OUT_IN_SECONDS).click();
        return new HomePage();
    }

    private static User createUser(@NotNull final String name, @NotNull final String login, @NotNull final String password) {
        return new UserHandler()
                .setPassword(password)
                .setLogin(login)
                .setName(name)
                .build();
    }
}
