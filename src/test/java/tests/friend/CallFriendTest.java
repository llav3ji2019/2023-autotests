package tests.friend;

import java.time.Duration;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import page.call.CallPage;
import page.home.HomePage;
import page.login.LoginPage;
import tests.BaseTest;
import utils.Exceptions.PersonNotFound;
import utils.user.User;
import utils.user.UserContainer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class CallFriendTest extends BaseTest {
    private static final String FRIEND_NAME = "Павел Емельянов";
    private static final User USER = new UserContainer().getUniqueUser();

    private HomePage homePage;

    @BeforeEach
    public void login() {
        homePage = new LoginPage().signIn(USER);
    }

    @Test
    @DisplayName("User calls to the friend test")
    public void callFriendTest() {
        try {
            CallPage callPage = homePage.openFriendPage()
                    .startPhoneCall(FRIEND_NAME);
            assertEquals(FRIEND_NAME, callPage.getPhoneCallPersonName(), "Person name should be " + FRIEND_NAME);
            callPage.finishPhoneCall();
            // Хотел сделать проверку на завершённый звонок, но он даже не начинался, поэтому нигде не отображается
            // Думал, что в журнале количество звонков будет инкрементироваться, но не нашёл журнал со звонками
            // Дальше думал проверить через чат с пользователем, которому звонишь, но не получилось
            // потому что если пользователь, которому звонишь не в сети, то звонок не будет отображён в чате
        }
        catch (PersonNotFound e) {
            fail("Person wasn't found");
        }
    }
}
