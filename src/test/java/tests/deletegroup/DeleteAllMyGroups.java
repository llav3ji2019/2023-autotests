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
    private HomePage homePage;
    private final User user = new UserContainer().getUniqueUser();

    @BeforeEach
    public void login() {
        homePage = new LoginPage().signIn(user);
    }

    @Test
    @DisplayName("Test for user deleting all his groups")
    public void deleteAllUsersGroupsTest() {
        homePage.openGroupPage().check().deleteAllGroups();
        assertThat("All groups are deleted", homePage.openGroupPage().areAllMyGroupsDeleted());
    }
}
