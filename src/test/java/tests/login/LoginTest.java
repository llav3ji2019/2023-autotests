package tests.login;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import page.home.HomePage;
import page.login.LoginPage;
import tests.BaseTest;
import tests.UserContainer;
import user.User;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class LoginTest extends BaseTest {
    private final LoginPage loginPage = new LoginPage();
    private HomePage homePage;
    private final User user = new UserContainer().getUniqueUser();

    @BeforeEach
    public void login() {
        homePage = loginPage.signIn(user);
    }

    @DisplayName("Test for user login")
    @Test
    public void loginTest() {
        assertTrue(homePage.checkPage(user));
    }
}
