import org.junit.jupiter.api.Test;

import login.LoginPage;

public class LoginTest extends BaseTest {
    @Test
    public void test() {
        LoginPage loginPage = new LoginPage();
        loginPage.openPage().signIn("79313643643", "pavel2003");
    }
}
