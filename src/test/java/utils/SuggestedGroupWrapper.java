package utils;

import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.By;

import com.codeborne.selenide.SelenideElement;

import page.LoadableComponent;

import static com.codeborne.selenide.Selectors.byXpath;

public class SuggestedGroupWrapper implements LoadableComponent {
    private static final long TIME_OUT_IN_SECONDS = 5;
    private static final By GROUP_NAME_BUTTON = byXpath(".//a[contains(@class, 'card_name')]");
    private static final By JOIN_GROUP_BUTTON = byXpath(".//a[contains(@class, 'group-join_btn')]");
    private static final String JOIN_GROUP_MESSAGE = "Can't join group";
    private static final String GROUP_NAME_MESSAGE = "Can't find group name";

    private final SelenideElement root;

    public SuggestedGroupWrapper(@NotNull final SelenideElement root) {
        this.root = root;
    }

    public String getGroupName() {
        return isLoaded(root.$(GROUP_NAME_BUTTON), GROUP_NAME_MESSAGE, TIME_OUT_IN_SECONDS).text();
    }

    public String joinGroup() {
        isLoaded(root.$(JOIN_GROUP_BUTTON), JOIN_GROUP_MESSAGE, TIME_OUT_IN_SECONDS).click();
        return getGroupName();
    }
}
