package page.friends;

import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.By;

import com.codeborne.selenide.SelenideElement;

import page.LoadableComponent;

import static com.codeborne.selenide.Selectors.byXpath;

public class FriendCard implements LoadableComponent {
    private static final long TIME_OUT_IN_SECONDS = 5;
    private static final By FRIEND_NAME = byXpath(".//a[@class='n-t bold']");
    private static final By CHAT_BUTTON = byXpath(".//a[@data-l='t,sendMessage']");
    private static final String FRIEND_CARD_MESSAGE = "Current friend card is not visible";
    private static final String FRIEND_NAME_MESSAGE = "Can't find name at current card";
    private static final String CHAT_BUTTON_MESSAGE = "Can't find send button";


    private final SelenideElement friendRoot;

    public FriendCard(@NotNull final SelenideElement friendRoot) {
        isLoaded(friendRoot, FRIEND_CARD_MESSAGE, TIME_OUT_IN_SECONDS);
        this.friendRoot = friendRoot;
    }

    public String getName() {
        return isLoaded(friendRoot.$(FRIEND_NAME), FRIEND_NAME_MESSAGE, TIME_OUT_IN_SECONDS).text();
    }

    public ChatPage openChatPage() {
        isLoaded(friendRoot.$(CHAT_BUTTON), CHAT_BUTTON_MESSAGE,TIME_OUT_IN_SECONDS).click();
        return new ChatPage();
    }
}
