package page.home;

import org.jetbrains.annotations.NotNull;

import com.codeborne.selenide.SelenideElement;

import page.LoadableComponent;
import page.friends.FriendsPage;
import page.group.GroupPage;
import page.login.LoginPage;
import utils.user.User;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;

public class HomePage implements LoadableComponent {
    private static final long TIME_OUT_IN_SECONDS = 5;
    private static final SelenideElement TOOLBAR = $x("//*[@class='navigation']");
    private static final SelenideElement PROFILE_NAME = $x("//a[contains(@data-l, 'userPage')]/div");
    private static final SelenideElement GROUP_BUTTON = $x("//a[@data-l='t,userAltGroup']");
    private static final SelenideElement EXIT_BAR = $x("//div[contains(@class, 'ucard-mini toolbar_ucard js-toolbar-menu')]");
    private static final SelenideElement LOG_OUT_BUTTON = $x("//*[contains(@data-l,'t,logout')]");
    private static final SelenideElement CONFIRM_EXIT_BUTTON = $x("//input[@data-l='t,logout']");
    private static final SelenideElement FRIENDS_BUTTON = $x("//a[@aria-label='Друзья' and @data-l='t,userFriend']");

    public HomePage() {
        check();
    }

    private void check() {
        isLoaded(TOOLBAR, "Can't find toolbar", TIME_OUT_IN_SECONDS);
    }

    public boolean checkPage(@NotNull final String name) {
        return doCheckPage(name);
    }

    public boolean checkPage(@NotNull final User user) {
        return doCheckPage(user.getFullName());
    }

    private boolean doCheckPage(@NotNull final String name) {
        return PROFILE_NAME.shouldBe(visible.because("Can't find user name at the page"))
                .text()
                .equals(name);
    }

    public LoginPage exit() {
        EXIT_BAR.shouldBe(visible.because("Can't find exit bar")).click();
        LOG_OUT_BUTTON.shouldBe(visible.because("Can't find log out button")).click();
        CONFIRM_EXIT_BUTTON.shouldBe(visible.because("Can't find confirm button")).click();
        return new LoginPage();
    }

    public FriendsPage openFriendPage() {
        FRIENDS_BUTTON.shouldBe(visible.because("Friends navigation button can't be found")).click();
        return new FriendsPage();
    }

    public GroupPage openGroupPage() {
        GROUP_BUTTON.shouldBe(visible.because("Can't find groups button")).click();
        return new GroupPage();
    }

}
