package page.login;

import java.util.Objects;

import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.bidi.log.Log;

import com.codeborne.selenide.SelenideElement;

import page.LoadableComponent;
import page.home.HomePage;
import tests.login.LoginTest;
import utils.user.User;
import utils.user.UserHandler;

import static com.codeborne.selenide.Selenide.$x;

public class LoginPage implements LoadableComponent {
    private static final long TIME_OUT_IN_SECONDS = 5;
    private static final SelenideElement LOGIN_INPUT = $x("//input[@id='field_email']");
    private static final SelenideElement PASSWORD_INPUT = $x("//input[@id='field_password']");
    private static final SelenideElement SIGN_IN_BUTTON = $x("//input[@data-l='t,sign_in']");
    private static final String LOGIN_INPUT_MESSAGE = "Can't find login input";
    private static final String PASSWORD_INPUT_MESSAGE = "Can't find password input";
    private static final String SIGN_IN_BUTTON_MESSAGE = "Can't find sign in button";
    private static final String SIGN_IN_BUTTON_NAME = "Войти в Одноклассники";

    public LoginPage() {
        check();
    }

    private void check() {
        isLoaded(LOGIN_INPUT, LOGIN_INPUT_MESSAGE, TIME_OUT_IN_SECONDS);
        isLoaded(PASSWORD_INPUT, PASSWORD_INPUT_MESSAGE, TIME_OUT_IN_SECONDS);
        isLoaded(SIGN_IN_BUTTON, SIGN_IN_BUTTON_MESSAGE, TIME_OUT_IN_SECONDS);
    }

    public HomePage signIn(@NotNull final User user) {
        return doSignIn(user);
    }

    public HomePage signIn(@NotNull final String name, @NotNull final String login, @NotNull final String password) {
        return signIn(createUser(name, login, password));
    }

    private HomePage doSignIn(@NotNull final User user) {
        isLoaded(LOGIN_INPUT, LOGIN_INPUT_MESSAGE, TIME_OUT_IN_SECONDS).setValue(user.getLogin());
        isLoaded(PASSWORD_INPUT, PASSWORD_INPUT_MESSAGE, TIME_OUT_IN_SECONDS).setValue(user.getPassword());
        isLoaded(SIGN_IN_BUTTON, SIGN_IN_BUTTON_MESSAGE, TIME_OUT_IN_SECONDS).click();
        return new HomePage();
    }

    private static User createUser(@NotNull final String name, @NotNull final String login, @NotNull final String password) {
        return new UserHandler()
                .setPassword(password)
                .setLogin(login)
                .setName(name)
                .build();
    }

    public boolean checkPage() {
        return Objects.equals(isLoaded(SIGN_IN_BUTTON, SIGN_IN_BUTTON_MESSAGE, TIME_OUT_IN_SECONDS)
                .getValue(), SIGN_IN_BUTTON_NAME);
    }
}
