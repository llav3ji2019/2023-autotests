package utils;

import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.By;

import com.codeborne.selenide.SelenideElement;

import page.LoadableComponent;
import page.profilegroup.ProfileGroupPage;

import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$x;

public class MyGroupWrapper  implements LoadableComponent {
    private static final long TIME_OUT_IN_SECONDS = 5;
    private static final By MY_GROUP_BUTTON = byXpath(".//a[@data-l='t,visit' and contains(@hrefattrs, 'UserGroups_MyGroupsNav_OpenItem')]");
    private static final SelenideElement MY_GROUP_TITLE_BUTTON = $x(".//a[@data-l='t,group']");
    private static final String MY_GROUP_MESSAGE = "Can't find my group";
    private static final String MY_GROUP_TITLE_MESSAGE = "Can't find group title";

    private final SelenideElement root;

    public MyGroupWrapper(@NotNull final SelenideElement root) {
        this.root = root;
    }

    public String getGroupName() {
        isLoaded(root.$(MY_GROUP_BUTTON), MY_GROUP_MESSAGE, TIME_OUT_IN_SECONDS).hover();
        return isLoaded(MY_GROUP_TITLE_BUTTON, MY_GROUP_TITLE_MESSAGE, TIME_OUT_IN_SECONDS).text();
    }

    public ProfileGroupPage deleteGroup() {
        isLoaded(root.$(MY_GROUP_BUTTON), MY_GROUP_MESSAGE, TIME_OUT_IN_SECONDS).click();
        return new ProfileGroupPage();
    }
}
