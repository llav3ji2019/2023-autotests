package tests.group;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import page.group.GroupPage;
import page.home.HomePage;
import page.login.LoginPage;
import tests.BaseTest;
import utils.user.User;
import utils.user.UserContainer;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DeleteAllMyGroups extends BaseTest {
    private static final String YOUR_GROUPS_IS_EMPTY_ERROR = "Your groups shouldn't be empty";
    private static final String GROUPS_IS_DELETED = "All groups should be deleted";
    private static final User USER = new UserContainer().getUniqueUser();

    private HomePage homePage;

    @BeforeEach
    public void login() {
        homePage = new LoginPage().signIn(USER);
    }

    @DisplayName("User deletes all their groups")
    @RepeatedTest(value = 2, name = "Test {displayName} Number {currentRepetition}/{totalRepetitions}")
    public void deleteAllUsersGroupsTest() {
        GroupPage groupPage = homePage.openGroupPage().check();
        assertThat(YOUR_GROUPS_IS_EMPTY_ERROR, !groupPage.areAllMyGroupsDeleted());
        groupPage.deleteAllGroups();
        assertThat(GROUPS_IS_DELETED, groupPage.areAllMyGroupsDeleted());
    }
}
