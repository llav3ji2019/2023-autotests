package tests.friend;

import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import page.chat.ChatPage;
import page.home.HomePage;
import page.login.LoginPage;
import tests.BaseTest;
import utils.user.UserContainer;
import utils.user.User;

import static org.hamcrest.MatcherAssert.assertThat;

public class MessageTest extends BaseTest {
    private static final String FRIEND_NAME = "Павел Емельянов";
    private static final String WRONG_CHAT_TITLE = "We are trying to send message to wrong friend";
    private static final String LAST_MESSAGE_SENT_MESSAGE = "Message wasn't sent";
    private static final User USER = new UserContainer().getUniqueUser();

    private HomePage homePage;

    @BeforeEach
    public void login() {
        homePage = new LoginPage().signIn(USER);
    }

    @ParameterizedTest(name = "Send message {0}")
    @ValueSource(strings = {"Hello world!", "haha", "hi"})
    @DisplayName("User sends a message to the friend")
    public void sendMessageTest(@NotNull final String text) {
        ChatPage chatPage = homePage.openFriendPage().check().openChat(FRIEND_NAME);
        assertThat(WRONG_CHAT_TITLE, chatPage.getChatTitle().equals(FRIEND_NAME));
        chatPage.sendMessage(text);
        assertThat(LAST_MESSAGE_SENT_MESSAGE, text.equals(chatPage.getLastMessageText()));
    }
}
