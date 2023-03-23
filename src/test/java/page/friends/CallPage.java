package page.friends;

import com.codeborne.selenide.SelenideElement;

import page.LoadableComponent;

import static com.codeborne.selenide.Selenide.$x;

public class CallPage implements LoadableComponent {
    private static final long TIME_OUT_IN_SECONDS = 5;
    private static final SelenideElement FINISH_CONVERSATION_BUTTON = $x("//msg-button[@title='Завершить разговор']");
    private static final SelenideElement VIDEO_BUTTON = $x("//msg-button[@title='Выключить видео']");
    private static final SelenideElement MICROPHONE_BUTTON = $x("//msg-button[@title='Выключить микрофон']");
    private static final SelenideElement CHAT_BUTTON = $x("//msg-button[@title='Отправить сообщение']");
    private static final SelenideElement POSITION_BUTTON = $x("//msg-button[@title='Свернуть окно звонка']");
    private static final SelenideElement DISPLAY_MODE_BUTTON = $x("//msg-button[@title='Включить полноэкранный режим']");

    private static final String FINISH_CONVERSATION_MESSAGE = "Can't find finish conversation button";
    private static final String VIDEO_MESSAGE = "Can't find video button";
    private static final String MICROPHONE_MESSAGE = "Can't find microphone button";
    private static final String CHAT_MESSAGE = "Can't find chat button";
    private static final String POSITION_MESSAGE = "Can't find position mode button";
    private static final String DISPLAY_MODE_MESSAGE = "Can't find display mode button";

    public CallPage check() {
        isLoaded(FINISH_CONVERSATION_BUTTON, FINISH_CONVERSATION_MESSAGE,TIME_OUT_IN_SECONDS);
        isLoaded(VIDEO_BUTTON, VIDEO_MESSAGE,TIME_OUT_IN_SECONDS);
        isLoaded(MICROPHONE_BUTTON, MICROPHONE_MESSAGE,TIME_OUT_IN_SECONDS);
        isLoaded(CHAT_BUTTON, CHAT_MESSAGE,TIME_OUT_IN_SECONDS);
        isLoaded(POSITION_BUTTON, POSITION_MESSAGE,TIME_OUT_IN_SECONDS);
        isLoaded(DISPLAY_MODE_BUTTON, DISPLAY_MODE_MESSAGE,TIME_OUT_IN_SECONDS);
        return this;
    }

    public FriendsPage finishPhoneCall() {
        isLoaded(FINISH_CONVERSATION_BUTTON, FINISH_CONVERSATION_MESSAGE,TIME_OUT_IN_SECONDS).click();
        return new FriendsPage();
    }
}
