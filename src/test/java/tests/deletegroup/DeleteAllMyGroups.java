package tests.deletegroup;

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
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DeleteAllMyGroups extends BaseTest {
    private static final String YOUR_GROUPS_IS_EMPTY_ERROR = "Your groups shouldn't be empty";
    private static final String GROUPS_IS_DELETED = "All groups should be deleted";

    private HomePage homePage;
    private final User user = new UserContainer().getUniqueUser();

    @BeforeEach
    public void login() {
        homePage = new LoginPage().signIn(user);
    }

    @Test
    @DisplayName("Test for user deleting all his groups")
    public void deleteAllUsersGroupsTest() {
        GroupPage groupPage = homePage.openGroupPage().check();
        assertThat(YOUR_GROUPS_IS_EMPTY_ERROR, !groupPage.areAllMyGroupsDeleted());
        groupPage.deleteAllGroups();
        assertThat(GROUPS_IS_DELETED, groupPage.areAllMyGroupsDeleted());
    }
}
