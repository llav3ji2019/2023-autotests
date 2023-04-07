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

import static org.apache.commons.lang3.BooleanUtils.isTrue;
import static org.hamcrest.MatcherAssert.assertThat;

public class MessageTest extends BaseTest {
    private static final String FRIEND_NAME = "Павел Емельянов";
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
        assertThat("We are trying to send message to wrong friend", isTrue(chatPage.getChatTitle().equals(FRIEND_NAME)));
        chatPage.sendMessage(text);
        assertThat("Message wasn't sent", isTrue(text.equals(chatPage.getLastMessageText())));
    }
}
