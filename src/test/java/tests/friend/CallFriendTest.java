package tests.friend;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import page.home.HomePage;
import page.login.LoginPage;
import tests.BaseTest;
import utils.user.User;
import utils.user.UserContainer;

public class CallFriendTest extends BaseTest {
    private static final String FRIEND_NAME = "Павел Емельянов";

    private HomePage homePage;
    private final User user = new UserContainer().getUniqueUser();

    @BeforeEach
    public void login() {
        homePage = new LoginPage().signIn(user);
    }

    @Test
    @DisplayName("User calls to the friend test")
    public void callFriendTest() {
        homePage.openFriendPage()
                .check()
                .startPhoneCall(FRIEND_NAME)
                .check()
                .finishPhoneCall()
                .check();
    }
}
