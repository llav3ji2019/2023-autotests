package tests.callfriend;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import page.home.HomePage;
import page.login.LoginPage;
import tests.BaseTest;
import user.User;
import user.UserContainer;

public class CallFriendTest extends BaseTest {
    private static final String FRIEND_NAME = "Павел Емельянов";

    private HomePage homePage;
    private final User user = new UserContainer().getUniqueUser();

    @BeforeEach
    public void login() {
        homePage = new LoginPage().signIn(user);
    }

    @Test
    @DisplayName("Test for user calling to a friend")
    public void sendMessageTest() {
        homePage.openFriendPage()
                .check()
                .startPhoneCall(FRIEND_NAME)
                .check()
                .finishPhoneCall()
                .check();
    }
}
