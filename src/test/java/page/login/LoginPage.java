package page.login;

import com.codeborne.selenide.SelenideElement;

import page.home.HomePage;
import user.OkUserBuilder;
import user.User;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.open;

public class LoginPage {
    private static final String LOGIN_PAGE_URL = "https://ok.ru/dk?st.cmd=anonymMain&st.layer.cmd=PopLayerClose";

    private final SelenideElement loginInput = $x("//input[@id='field_email']");
    private final SelenideElement passwordInput = $x("//input[@id='field_password']");
    private final SelenideElement signInButton = $x("//input[@type='submit' and @class='button-pro __wide']");

    public LoginPage openPage() {
        open(LOGIN_PAGE_URL);
        return this;
    }

    public HomePage signIn(User user) {
        loginInput.setValue(user.getLogin());
        passwordInput.setValue(user.getPassword());
        signInButton.click();
        return new HomePage();
    }

    public HomePage signIn(final String name, final String login, final String password) {
        User user = new OkUserBuilder()
                .setPassword(password)
                .setLogin(login)
                .setName(name)
                .build();
        return signIn(user);
    }
}
