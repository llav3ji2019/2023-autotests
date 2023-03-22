package page.home;

import org.jetbrains.annotations.NotNull;

import com.codeborne.selenide.SelenideElement;

import page.LoadableComponent;
import page.login.LoginPage;
import user.User;

import static com.codeborne.selenide.Selenide.$x;

public class HomePage implements LoadableComponent {
    private static final long TIME_OUT_IN_SECONDS = 5;
    private final SelenideElement uniqueBlock = $x("//a[contains(@data-l, 'userPage')]/div");
    private final SelenideElement exitBar = $x("//div[contains(@class, 'ucard-mini toolbar_ucard js-toolbar-menu')]");
    private final SelenideElement logOutButton = $x("//*[contains(@data-l,'t,logout')]");
    private final SelenideElement confirmExitButton = $x("//input[@data-l='t,logout']");

    public boolean checkPage(@NotNull final String name) {
        return doCheckPage(name);
    }

    public boolean checkPage(@NotNull final User user) {
        return doCheckPage(user.getFullName());
    }

    private boolean doCheckPage(@NotNull final String name) {
        return isLoaded(uniqueBlock, TIME_OUT_IN_SECONDS)
                .text()
                .equals(name);
    }

    public LoginPage exit() {
        isLoaded(exitBar, TIME_OUT_IN_SECONDS).click();
        isLoaded(logOutButton, TIME_OUT_IN_SECONDS).click();
        isLoaded(confirmExitButton, TIME_OUT_IN_SECONDS).click();
        return new LoginPage();
    }
}
