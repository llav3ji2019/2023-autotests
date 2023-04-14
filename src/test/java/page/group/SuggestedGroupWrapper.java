package page.group;

import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.By;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import page.LoadableComponent;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byXpath;

public class SuggestedGroupWrapper implements LoadableComponent {
    private static final long TIME_OUT_IN_SECONDS = 5;
    private static final By GROUP_NAME_BUTTON = byXpath(".//a[contains(@class, 'card_name')]");
    private static final By JOIN_GROUP_BUTTON = byXpath(".//a[contains(@class, 'group-join_btn')]");

    private final SelenideElement root;

    public SuggestedGroupWrapper(@NotNull final SelenideElement root) {
        isLoaded(root, "Can't find group card", TIME_OUT_IN_SECONDS);
        this.root = root;
    }

    public String getGroupName() {
        return root.$(GROUP_NAME_BUTTON).shouldBe(visible.because("Can't find group name")).text();
    }

    public void joinGroup() {
        root.$(JOIN_GROUP_BUTTON).shouldBe(visible.because("Can't join group")).click();
    }
}
