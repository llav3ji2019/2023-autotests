package tests.messages;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import page.friends.ChatPage;
import page.home.HomePage;
import page.login.LoginPage;
import tests.BaseTest;
import tests.UserContainer;
import user.User;

import static java.util.concurrent.CompletableFuture.anyOf;
import static org.apache.commons.lang3.StringUtils.startsWith;
import static org.hamcrest.MatcherAssert.assertThat;

public class MessageTest extends BaseTest {
    private static final String FRIEND_NAME = "Павел Емельянов";
    private static final String DEFAULT_TEXT = "Hello world!";
    private static final String WRONG_CHAT_TITLE = "We are trying to send message to wrong friend";

    private HomePage homePage;
    private final User user = new UserContainer().getUniqueUser();

    @BeforeEach
    public void login() {
        homePage = new LoginPage().signIn(user);
    }

    @Test
    public void sendMessageTest(){
        ChatPage chatPage = homePage.openFriendPage().check().openChat(FRIEND_NAME);
        assertThat(WRONG_CHAT_TITLE, chatPage.getChatTitle().equals(FRIEND_NAME));
        chatPage.sendMessage(DEFAULT_TEXT);
    }
}
