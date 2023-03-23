package tests.joingroup;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import page.group.GroupPage;
import page.home.HomePage;
import page.login.LoginPage;
import tests.BaseTest;
import user.User;
import user.UserContainer;

import static org.hamcrest.MatcherAssert.assertThat;

public class JoinGroupTest extends BaseTest {
    private static final String GROUP_IS_ADDED_MESSAGE = "Added Group should be in list";

    private HomePage homePage;
    private final User user = new UserContainer().getUniqueUser();

    @BeforeEach
    public void login() {
        homePage = new LoginPage().signIn(user);
    }

    @Test
    @DisplayName("Test for user joining to the first group")
    public void joinGroupTest() {
        GroupPage groupPage = homePage.openGroupPage().check();
        String newGroupName = groupPage.joinRandomGroup().getNewGroupName();
        groupPage.refresh();
        assertThat(GROUP_IS_ADDED_MESSAGE, groupPage.isGroupAddedToMyGroupsList(newGroupName));
    }
}
