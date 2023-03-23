package page.home;

import org.jetbrains.annotations.NotNull;

import com.codeborne.selenide.SelenideElement;

import page.LoadableComponent;
import page.friends.FriendsPage;
import page.login.LoginPage;
import user.User;

import static com.codeborne.selenide.Selenide.$x;

public class HomePage implements LoadableComponent {
    private static final long TIME_OUT_IN_SECONDS = 5;
    private static final SelenideElement PROFILE_NAME = $x("//a[contains(@data-l, 'userPage')]/div");
    private static final SelenideElement EXIT_BAR = $x("//div[contains(@class, 'ucard-mini toolbar_ucard js-toolbar-menu')]");
    private static final SelenideElement LOG_OUT_BUTTON = $x("//*[contains(@data-l,'t,logout')]");
    private static final SelenideElement CONFIRM_EXIT_BUTTON = $x("//input[@data-l='t,logout']");
    private static final SelenideElement FRIENDS_BUTTON = $x("//a[@aria-label='Друзья' and @data-l='t,userFriend']");
    private static final String PROFILE_NAME_MESSAGE = "Can't find user name at the page";
    private static final String EXIT_BAR_MESSAGE = "Can't find exit bar";
    private static final String LOG_OUT_BUTTON_MESSAGE = "Can't find log out button";
    private static final String CONFIRM_EXIT_BUTTON_MESSAGE = "Can't find confirm button";
    private static final String FRIENDS_BUTTON_MESSAGE = "Friends navigation button can't be found";

    public boolean checkPage(@NotNull final String name) {
        return doCheckPage(name);
    }

    public boolean checkPage(@NotNull final User user) {
        return doCheckPage(user.getFullName());
    }

    private boolean doCheckPage(@NotNull final String name) {
        return isLoaded(PROFILE_NAME, PROFILE_NAME_MESSAGE, TIME_OUT_IN_SECONDS)
                .text()
                .equals(name);
    }

    public LoginPage exit() {
        isLoaded(EXIT_BAR, EXIT_BAR_MESSAGE, TIME_OUT_IN_SECONDS).click();
        isLoaded(LOG_OUT_BUTTON, LOG_OUT_BUTTON_MESSAGE,TIME_OUT_IN_SECONDS).click();
        isLoaded(CONFIRM_EXIT_BUTTON, CONFIRM_EXIT_BUTTON_MESSAGE,TIME_OUT_IN_SECONDS).click();
        return new LoginPage();
    }

    public FriendsPage openFriendPage() {
        isLoaded(FRIENDS_BUTTON, FRIENDS_BUTTON_MESSAGE,TIME_OUT_IN_SECONDS).click();
        return new FriendsPage();
    }

}
