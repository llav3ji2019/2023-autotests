package page.friends;

import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.By;

import com.codeborne.selenide.SelenideElement;

import page.LoadableComponent;
import page.chat.ChatPage;
import page.call.CallPage;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byXpath;

public class FriendWrapper implements LoadableComponent {
    private static final long TIME_OUT_IN_SECONDS = 5;
    private static final By FRIEND_NAME = byXpath(".//a[@class='n-t bold']");
    private static final By CHAT_BUTTON = byXpath(".//a[@data-l='t,sendMessage']");
    private static final By CALL_BUTTON = byXpath(".//a[@data-l='t,call']");

    private final SelenideElement friendRoot;

    public FriendWrapper(@NotNull final SelenideElement friendRoot) {
        isLoaded(friendRoot, "Current friend card is not visible", TIME_OUT_IN_SECONDS);
        this.friendRoot = friendRoot;
    }

    public String getName() {
        return friendRoot.$(FRIEND_NAME).shouldBe(visible.because("Can't find name at current card")).text();
    }

    public ChatPage openChatPage() {
        friendRoot.$(CHAT_BUTTON).shouldBe(visible.because("Can't find send button")).click();
        return new ChatPage();
    }

    public CallPage openCallPage() {
        friendRoot.$(CALL_BUTTON).shouldBe(visible.because("Can't find call button")).click();
        return new CallPage();
    }
}
