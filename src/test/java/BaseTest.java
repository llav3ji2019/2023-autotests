import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {
    private static final String BROWSER = "chrome";
    private static final String SCREEN_EXTENSION = "1920*1080";

    @BeforeEach
    public void init() {
        setUp();
    }

    @AfterEach
    public void tearDown() {
        Selenide.closeWebDriver();
    }

    private void setUp() {
        WebDriverManager.chromedriver().setup();
        Configuration.browser = BROWSER;
        Configuration.driverManagerEnabled = true;
        Configuration.browserSize = SCREEN_EXTENSION;
        Configuration.headless = false;
    }
}
