package page.chat;

import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.Keys;

import com.codeborne.selenide.SelenideElement;

import page.LoadableComponent;

import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;
import static org.hamcrest.MatcherAssert.assertThat;

public class ChatPage implements LoadableComponent {
    private static final long TIME_OUT_IN_SECONDS = 5;
    private static final SelenideElement CHAT_TITLE = $x("//span[@data-tsid='chat_title']");
    private static final SelenideElement TEXT_INPUT = $x("//div[@data-tsid='write_msg_input-input']");
    private static final SelenideElement LAST_MESSAGE_SENT = $$x("//*[@data-tsid='message_text']").last();
    private static final String CHAT_ITEM_MESSAGE = "It is not needed user";
    private static final String TEXT_INPUT_MESSAGE = "Can't find input where i can enter some text";
    private static final String LAST_SENT_MESSAGE = "Can't find message";

    public ChatPage() {
        check();
    }

    private void check() {
        isLoaded(CHAT_TITLE, CHAT_ITEM_MESSAGE, TIME_OUT_IN_SECONDS);
        isLoaded(TEXT_INPUT, TEXT_INPUT_MESSAGE, TIME_OUT_IN_SECONDS);
    }

    public String getChatTitle() {
        return isLoaded(CHAT_TITLE, CHAT_ITEM_MESSAGE, TIME_OUT_IN_SECONDS).text();
    }

    public String getLastMessageText() {
        return isLoaded(LAST_MESSAGE_SENT, LAST_SENT_MESSAGE, TIME_OUT_IN_SECONDS).text();
    }

    public ChatPage sendMessage(@NotNull final String myMessage) {
        isLoaded(TEXT_INPUT, TEXT_INPUT_MESSAGE, TIME_OUT_IN_SECONDS).setValue(myMessage).sendKeys(Keys.ENTER);
        return this;
    }
}
