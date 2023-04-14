package page.call;

import java.time.Duration;

import com.codeborne.selenide.SelenideElement;

import page.LoadableComponent;
import page.friends.FriendsPage;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;

public class CallPage implements LoadableComponent {
    private static final long TIME_OUT_IN_SECONDS = 5;
    private static final SelenideElement FINISH_CONVERSATION_BUTTON = $x("//msg-button[@title='Завершить разговор']");
    private static final SelenideElement VIDEO_BUTTON = $x("//msg-button[@title='Выключить видео']");
    private static final SelenideElement MICROPHONE_BUTTON = $x("//msg-button[@title='Выключить микрофон']");
    private static final SelenideElement CHAT_BUTTON = $x("//msg-button[@title='Отправить сообщение']");
    private static final SelenideElement POSITION_BUTTON = $x("//msg-button[@title='Свернуть окно звонка']");
    private static final SelenideElement DISPLAY_MODE_BUTTON = $x("//msg-button[@title='Включить полноэкранный режим']");

    public CallPage() {
        check();
    }

    private void check() {
        isLoaded(FINISH_CONVERSATION_BUTTON, "Can't find finish conversation button",TIME_OUT_IN_SECONDS);
        isLoaded(VIDEO_BUTTON, "Can't find video button",TIME_OUT_IN_SECONDS);
        isLoaded(MICROPHONE_BUTTON, "Can't find microphone button",TIME_OUT_IN_SECONDS);
        isLoaded(CHAT_BUTTON, "Can't find chat button",TIME_OUT_IN_SECONDS);
        isLoaded(POSITION_BUTTON, "Can't find position mode button",TIME_OUT_IN_SECONDS);
        isLoaded(DISPLAY_MODE_BUTTON, "Can't find display mode button",TIME_OUT_IN_SECONDS);
    }

    public FriendsPage finishPhoneCall() {
        FINISH_CONVERSATION_BUTTON.shouldBe(visible.because("Can't find finish conversation button")).click();
        return new FriendsPage();
    }
}
