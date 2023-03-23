package tests.messages;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import page.home.HomePage;
import page.login.LoginPage;
import tests.BaseTest;
import tests.UserContainer;
import user.User;

public class MessageTest extends BaseTest {
    private HomePage homePage;
    private final User user = new UserContainer().getUniqueUser();

    @BeforeEach
    public void login() {
        homePage = new LoginPage().signIn(user);
    }

    @Test
    public void sendMessageTest(){
        homePage.openFriendPage().check().openChat("Павел Емельянов").sendMessage();
    }
}
