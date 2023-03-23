package page.profilegroup;

import com.codeborne.selenide.SelenideElement;

import page.LoadableComponent;
import page.group.GroupPage;

import static com.codeborne.selenide.Selenide.$x;

public class ProfileGroupPage implements LoadableComponent {
    private static final long TIME_OUT_IN_SECONDS = 5;
    private static final SelenideElement GROUP_BUTTON = $x("//div[@class='dropdown __wide h-mod']");
    private static final SelenideElement EXIT_BUTTON = $x("//a[@class='dropdown_i']");
    private static final SelenideElement NEWS_BUTTON = $x("//a[text()='Лента']");
    private static final SelenideElement CONFIRM_BUTTON = $x("//input[@data-l='t,confirm']");
    private static final SelenideElement USERS_BUTTON = $x("//a[contains(text(),'Участники')]");
    private static final SelenideElement ALL_GROUPS_BUTTON = $x("//a[@data-l='t,userAltGroup']");
    private static final String GROUP_MESSAGE = "Can't find group button";
    private static final String EXIT_MESSAGE = "Can't find exit button";
    private static final String NEWS_MESSAGE = "Can't see news button of this group";
    private static final String CONFIRM_MESSAGE = "Can't find confirm button";
    private static final String USERS_MESSAGE = "Can't see users button in this group";
    private static final String ALL_GROUPS_MESSAGE = "Can't find groups button";

    public ProfileGroupPage check() {
        isLoaded(NEWS_BUTTON, NEWS_MESSAGE, TIME_OUT_IN_SECONDS);
        isLoaded(USERS_BUTTON, USERS_MESSAGE, TIME_OUT_IN_SECONDS);
        return this;
    }

    public ProfileGroupPage exitFromGroup() {
        isLoaded(GROUP_BUTTON, GROUP_MESSAGE, TIME_OUT_IN_SECONDS).click();
        isLoaded(EXIT_BUTTON, EXIT_MESSAGE, TIME_OUT_IN_SECONDS).click();
        isLoaded(CONFIRM_BUTTON, CONFIRM_MESSAGE, TIME_OUT_IN_SECONDS).click();
        return this;
    }

    public GroupPage goToGroupsPage() {
        isLoaded(ALL_GROUPS_BUTTON, ALL_GROUPS_MESSAGE, TIME_OUT_IN_SECONDS).click();
        return new GroupPage();
    }
}
