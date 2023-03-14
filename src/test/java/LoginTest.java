import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import page.home.HomePage;
import page.login.LoginPage;

import static org.junit.jupiter.api.Assertions.*;

public class LoginTest extends BaseTest {
    private final LoginPage loginPage = new LoginPage();
    private HomePage homePage;

    @BeforeEach
    public void login() {
        homePage = loginPage.openPage().signIn("Павел Емельянов","79*********", "******");
    }

    @Test
    public void test() {
        assertTrue(homePage.check("Павел Емельянов"));
    }
}
