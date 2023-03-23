package page.friends;

import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.Keys;

import com.codeborne.selenide.SelenideElement;

import page.LoadableComponent;
import user.User;

import static com.codeborne.selenide.Selenide.$x;

public class ChatPage implements LoadableComponent {
    private static final long TIME_OUT_IN_SECONDS = 5;
    private static final SelenideElement CHAT_TITLE = $x("//span[@data-tsid='chat_title']");
    private static final SelenideElement TEXT_INPUT = $x("//div[@data-tsid='write_msg_input-input']");
    private static final String CHAT_ITEM_MESSAGE = "It is not needed user";
    private static final String TEXT_INPUT_MESSAGE = "Can't find input where i can enter some text";

    public ChatPage check() {
        isLoaded(CHAT_TITLE, CHAT_ITEM_MESSAGE, TIME_OUT_IN_SECONDS);
        isLoaded(TEXT_INPUT, TEXT_INPUT_MESSAGE, TIME_OUT_IN_SECONDS);
        return this;
    }

    public String getChatTitle() {
        return isLoaded(CHAT_TITLE, CHAT_ITEM_MESSAGE, TIME_OUT_IN_SECONDS).text();
    }

    public ChatPage sendMessage(@NotNull final String text) {
        TEXT_INPUT.setValue(text).sendKeys(Keys.ENTER);
        return this;
    }
}
