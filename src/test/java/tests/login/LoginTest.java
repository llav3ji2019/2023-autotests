package tests.login;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import page.home.HomePage;
import page.login.LoginPage;
import tests.BaseTest;
import utils.user.UserContainer;
import utils.user.User;

import static org.apache.commons.lang3.BooleanUtils.isTrue;
import static org.hamcrest.MatcherAssert.assertThat;

public class LoginTest extends BaseTest {
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
        assertThat("User should see home page", isTrue(homePage.checkPage(USER)));
    }
}
