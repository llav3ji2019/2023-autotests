package tests.login;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import page.home.HomePage;
import page.login.LoginPage;
import tests.BaseTest;
import utils.user.UserContainer;
import utils.user.User;

import static org.hamcrest.MatcherAssert.assertThat;

public class LoginTest extends BaseTest {
    private static final String RESULT_PAGE = "User should see home page";
    private static final User USER = new UserContainer().getUniqueUser();

    private final LoginPage loginPage = new LoginPage();
    private HomePage homePage;

    @BeforeEach
    public void login() {
        homePage = loginPage.signIn(USER);
    }

    @DisplayName("Test for user login")
    @Test
    public void loginTest() {
        assertThat(RESULT_PAGE, homePage.checkPage(USER));
    }
}
