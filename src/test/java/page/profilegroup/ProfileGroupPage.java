package page.profilegroup;

import com.codeborne.selenide.SelenideElement;

import page.LoadableComponent;
import page.group.GroupPage;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;

public class ProfileGroupPage implements LoadableComponent {
    private static final long TIME_OUT_IN_SECONDS = 5;
    private static final SelenideElement GROUP_BUTTON = $x("//div[@class='dropdown __wide h-mod']");
    private static final SelenideElement EXIT_BUTTON = $x("//a[@class='dropdown_i']");
    private static final SelenideElement NEWS_BUTTON = $x("//a[text()='Лента']");
    private static final SelenideElement CONFIRM_BUTTON = $x("//input[@data-l='t,confirm']");
    private static final SelenideElement USERS_BUTTON = $x("//a[contains(text(),'Участники')]");
    private static final SelenideElement ALL_GROUPS_BUTTON = $x("//a[@data-l='t,userAltGroup']");

    public ProfileGroupPage() {
        check();
    }

    private void check() {
        isLoaded(NEWS_BUTTON, "Can't see news button of this group", TIME_OUT_IN_SECONDS);
        isLoaded(USERS_BUTTON, "Can't see users button in this group", TIME_OUT_IN_SECONDS);
    }

    public ProfileGroupPage exitFromGroup() {
        GROUP_BUTTON.shouldBe(visible.because("Can't find group button")).click();
        EXIT_BUTTON.shouldBe(visible.because("Can't find exit button")).click();
        CONFIRM_BUTTON.shouldBe(visible.because("Can't find confirm button")).click();
        return this;
    }

    public GroupPage goToGroupsPage() {
        ALL_GROUPS_BUTTON.shouldBe(visible.because("Can't find groups button")).click();
        return new GroupPage();
    }
}
