package tests.logout;

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

public class LogOutTest extends BaseTest {
    private static final User USER = new UserContainer().getUniqueUser();

    private HomePage homePage;

    @BeforeEach
    public void login() {
        homePage = new LoginPage().signIn(USER);
    }

    @Test
    @DisplayName("User exit from ok.ru test")
    public void logOutTest() {
        assertThat("Can't find sign in button", isTrue(homePage.exit().checkPage()));
    }

}
