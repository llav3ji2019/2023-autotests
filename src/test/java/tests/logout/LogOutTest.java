package tests.logout;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import page.home.HomePage;
import page.login.LoginPage;
import tests.BaseTest;
import tests.UserContainer;
import user.User;

public class LogOutTest extends BaseTest {
    HomePage homePage = null;
    private final User user = new UserContainer().getUniqueUser();

    @BeforeEach
    public void login() {
        homePage = new LoginPage().signIn(user);
    }

    @Test
    @DisplayName("Test for user exiting the messenger")
    public void logOutTest() {
        homePage.exit();
    }

}
