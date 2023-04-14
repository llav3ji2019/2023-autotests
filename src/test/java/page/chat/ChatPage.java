package page.chat;

import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.Keys;

import com.codeborne.selenide.SelenideElement;

import page.LoadableComponent;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;

public class ChatPage implements LoadableComponent {
    private static final long TIME_OUT_IN_SECONDS = 5;
    private static final SelenideElement CHAT_TITLE = $x("//span[@data-tsid='chat_title']");
    private static final SelenideElement TEXT_INPUT = $x("//div[@data-tsid='write_msg_input-input']");
    private static final SelenideElement AUDIO_CALL_BTN = $x("//*[@data-l='t,audioCall']");
    private static final SelenideElement VIDEO_CALL_BTN = $x("//*[@data-l='t,videoCall']");
    private static final SelenideElement SEARCH_BTN = $x("//*[@data-l='t,searchMessages']");
    private static final SelenideElement CREATE_CHAT_BTN = $x("//*[@data-l='t,createChatFromDialog']");
    private static final SelenideElement SHOW_CHAT_INFO_BTN = $x("//*[@data-l='t,showChatInfo']");
    private static final SelenideElement LAST_MESSAGE_SENT = $$x("//*[@data-tsid='message_text']").last();

    public ChatPage() {
        check();
    }

    private void check() {
        isLoaded(AUDIO_CALL_BTN, "Can't find audio call button", TIME_OUT_IN_SECONDS);
        isLoaded(VIDEO_CALL_BTN, "Can't find video call button", TIME_OUT_IN_SECONDS);
        isLoaded(SEARCH_BTN, "Can't find search button", TIME_OUT_IN_SECONDS);
        isLoaded(CREATE_CHAT_BTN, "Can't find chat creation button", TIME_OUT_IN_SECONDS);
        isLoaded(SHOW_CHAT_INFO_BTN, "Can't find info button", TIME_OUT_IN_SECONDS);
    }

    public String getChatTitle() {
        return CHAT_TITLE.shouldBe(visible.because("It is not needed user"))
                .text();
    }

    public String getLastMessageText() {
        return LAST_MESSAGE_SENT.shouldBe(visible.because("Can't find message"))
                .text();
    }

    public ChatPage sendMessage(@NotNull final String myMessage) {
        TEXT_INPUT.shouldBe(visible.because("Can't find input where i can enter some text"))
                .setValue(myMessage)
                .sendKeys(Keys.ENTER);
        return this;
    }
}
