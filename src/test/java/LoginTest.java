import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import page.home.HomePage;
import page.login.LoginPage;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class LoginTest extends BaseTest {
    private final LoginPage loginPage = new LoginPage();
    private HomePage homePage;

    @BeforeEach
    public void login() {
        homePage = loginPage.signIn(user);
    }

    @Test
    public void loginTest() {
        assertTrue(homePage.isLoaded(user));
    }
}
