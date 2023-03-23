package tests.logout;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import dev.failsafe.internal.util.Assert;
import page.home.HomePage;
import page.login.LoginPage;
import tests.BaseTest;
import tests.UserContainer;
import user.User;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class LogOutTest extends BaseTest {
    private HomePage homePage;
    private final User user = new UserContainer().getUniqueUser();

    @BeforeEach
    public void login() {
        homePage = new LoginPage().signIn(user);
    }

    @Test
    @DisplayName("Test for user exiting the messenger")
    public void logOutTest() {
        assertTrue(homePage.exit().checkPage());
    }

}
