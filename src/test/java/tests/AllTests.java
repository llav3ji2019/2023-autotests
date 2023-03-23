package tests;

import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

import tests.friend.CallFriendTest;
import tests.group.DeleteAllMyGroups;
import tests.group.JoinGroupTest;
import tests.login.LoginTest;
import tests.logout.LogOutTest;
import tests.friend.MessageTest;

@Suite
@SelectClasses({MessageTest.class, LoginTest.class, JoinGroupTest.class, CallFriendTest.class, LogOutTest.class, DeleteAllMyGroups.class})
public class AllTests {
}
