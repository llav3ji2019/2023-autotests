package page.group;

import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.By;

import com.codeborne.selenide.SelenideElement;

import page.LoadableComponent;
import page.profilegroup.ProfileGroupPage;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$x;

public class MyGroupWrapper  implements LoadableComponent {
    private static final long TIME_OUT_IN_SECONDS = 5;
    private static final By MY_GROUP_BUTTON = byXpath(".//a[@data-l='t,visit' and contains(@hrefattrs, 'UserGroups_MyGroupsNav_OpenItem')]");
    private static final SelenideElement MY_GROUP_TITLE_BUTTON = $x(".//a[@data-l='t,group']");

    private final SelenideElement root;

    public MyGroupWrapper(@NotNull final SelenideElement root) {
        isLoaded(root, "Can't find group card", TIME_OUT_IN_SECONDS);
        this.root = root;
    }

    public String getGroupName() {
        root.$(MY_GROUP_BUTTON).shouldBe(visible.because("Can't find my group")).hover();
        return MY_GROUP_TITLE_BUTTON.shouldBe(visible.because("Can't find group title")).text();
    }

    public ProfileGroupPage deleteGroup() {
        root.$(MY_GROUP_BUTTON).shouldBe(visible.because("Can't find my group")).click();
        return new ProfileGroupPage();
    }
}
