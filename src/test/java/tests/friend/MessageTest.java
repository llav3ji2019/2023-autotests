package tests.friend;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import page.chat.ChatPage;
import page.home.HomePage;
import page.login.LoginPage;
import tests.BaseTest;
import utils.user.UserContainer;
import utils.user.User;

import static org.hamcrest.MatcherAssert.assertThat;

public class MessageTest extends BaseTest {
    private static final String FRIEND_NAME = "Павел Емельянов";
    private static final String DEFAULT_TEXT = "Hello world!11";
    private static final String WRONG_CHAT_TITLE = "We are trying to send message to wrong friend";
    private static final String LAST_MESSAGE_SENT_MESSAGE = "Message wasn't sent";

    private HomePage homePage;
    private final User user = new UserContainer().getUniqueUser();

    @BeforeEach
    public void login() {
        homePage = new LoginPage().signIn(user);
    }

    @Test
    @DisplayName("User sends a message to the friend")
    public void sendMessageTest() {
        ChatPage chatPage = homePage.openFriendPage().check().openChat(FRIEND_NAME);
        assertThat(WRONG_CHAT_TITLE, chatPage.getChatTitle().equals(FRIEND_NAME));
        chatPage.sendMessage(DEFAULT_TEXT);
        assertThat(LAST_MESSAGE_SENT_MESSAGE, DEFAULT_TEXT.equals(chatPage.getLastMessageText()));
    }
}
