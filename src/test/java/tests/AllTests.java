package tests;

import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

import tests.callfriend.CallFriendTest;
import tests.deletegroup.DeleteAllMyGroups;
import tests.joingroup.JoinGroupTest;
import tests.login.LoginTest;
import tests.logout.LogOutTest;
import tests.messages.MessageTest;

@Suite
@SelectClasses({MessageTest.class, LoginTest.class, JoinGroupTest.class, CallFriendTest.class, LogOutTest.class, DeleteAllMyGroups.class})
public class AllTests {
}
