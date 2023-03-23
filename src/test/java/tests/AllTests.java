package tests;

import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

import tests.call.CallFriendTest;
import tests.login.LoginTest;
import tests.logout.LogOutTest;
import tests.messages.MessageTest;

@Suite
@SelectClasses({MessageTest.class, LoginTest.class, LogOutTest.class, CallFriendTest.class})
public class AllTests {
}
