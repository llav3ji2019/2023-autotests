package tests.group;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import page.group.GroupPage;
import page.home.HomePage;
import page.login.LoginPage;
import tests.BaseTest;
import utils.user.User;
import utils.user.UserContainer;

import static org.apache.commons.lang3.BooleanUtils.isTrue;
import static org.hamcrest.MatcherAssert.assertThat;

public class JoinGroupTest extends BaseTest {
    private static final String GROUP_IS_ADDED_MESSAGE = "Added Group should be in list";
    private static final User USER = new UserContainer().getUniqueUser();

    private HomePage homePage;

    @BeforeEach
    public void login() {
        homePage = new LoginPage().signIn(USER);
    }

    @Test
    @DisplayName("User join group")
    public void joinGroupTest() {
        GroupPage groupPage = homePage.openGroupPage().check();
        String newGroupName = groupPage.joinRandomGroup().getNewGroupName();
        groupPage.refresh();
        assertThat(GROUP_IS_ADDED_MESSAGE, isTrue(groupPage.isGroupAddedToMyGroupsList(newGroupName)));
    }
}
