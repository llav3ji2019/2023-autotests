package tests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;

import io.github.bonigarcia.wdm.WebDriverManager;

import static com.codeborne.selenide.Selenide.open;

public class BaseTest {
    private static final String LOGIN_PAGE_URL = "https://ok.ru/";
    private static final String BROWSER = "chrome";
    private static final String SCREEN_EXTENSION = "1920*1080";

    @BeforeEach
    public void init() {
        doInit();
    }

    @AfterEach
    public void exit() {
        Selenide.closeWebDriver();
    }

    private void doInit() {
        WebDriverManager.chromedriver().setup();
        Configuration.browser = BROWSER;
        Configuration.driverManagerEnabled = true;
        Configuration.browserSize = SCREEN_EXTENSION;
        Configuration.headless = false;
        open(LOGIN_PAGE_URL);
    }
}
