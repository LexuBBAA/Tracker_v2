package com.tracker.auth.ws;

import com.tracker.auth.ws.utils.UserEnrollValidator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import sun.jvm.hotspot.utilities.Assert;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserEnrollValidatorTests {

	@Autowired
	private UserEnrollValidator userEnrollValidator;

	@Test
	public void shouldCorrectlyValidateUsername() {
		Assert.that(userEnrollValidator.validateUsername("user"), "Test failed for input: \"user\"");
		Assert.that(userEnrollValidator.validateUsername("UserTest2019"), "Test failed for input: \"UserTest2019\"");
		Assert.that(userEnrollValidator.validateUsername("user_2019"), "Test failed for input: \"user_2019\"");
		Assert.that(userEnrollValidator.validateUsername("User_test"), "Test failed for input: \"User_test\"");
		Assert.that(userEnrollValidator.validateUsername("User-2019"), "Test failed for input: \"User-2019\"");
		Assert.that(userEnrollValidator.validateUsername("User.2019"), "Test failed for input: \"User.2019\"");
		Assert.that(!userEnrollValidator.validateUsername("User#"), "Test failed for input: \"User#\"");
		Assert.that(!userEnrollValidator.validateUsername("@Test2019"), "Test failed for input: \"@Test2019\"");
		Assert.that(!userEnrollValidator.validateUsername(".User2019"), "Test failed for input: \".User2019\"");
		Assert.that(!userEnrollValidator.validateUsername("UserTest2019."), "Test failed for input: \"UserTest2019.\"");
		Assert.that(!userEnrollValidator.validateUsername("UserTest2019_"), "Test failed for input: \"UserTest2019_\"");
		Assert.that(!userEnrollValidator.validateUsername("_UserTest2019"), "Test failed for input: \"_UserTest2019\"");
		Assert.that(!userEnrollValidator.validateUsername(""), "Test failed for input: \"\"");
		Assert.that(!userEnrollValidator.validateUsername(null), "Test failed for input: null");
	}

	@Test
	public void shouldCorrectlyValidateEmail() {
		Assert.that(userEnrollValidator.validateEmail("user@domain.com"), "Test failed for input: \"user@domain.com\"");
		Assert.that(userEnrollValidator.validateEmail("User2019@domain.eu"), "Test failed for input: \"User2019@domain.eu\"");
		Assert.that(userEnrollValidator.validateEmail("User_2019@domain.eu"), "Test failed for input: \"User2019@domain.eu\"");
		Assert.that(userEnrollValidator.validateEmail("user.2019@domain.eu"), "Test failed for input: \"user.2019@domain.eu\"");
		Assert.that(userEnrollValidator.validateEmail("user.test_2019@domain.eu"), "Test failed for input: \"user.test_2019@domain.eu\"");
		Assert.that(!userEnrollValidator.validateEmail("admin.Backend@tracker_v2.local"), "Test failed for input: \"admin.Backend@tracker_v2.local\"");
		Assert.that(!userEnrollValidator.validateEmail("User@2019@domain.com"), "Test failed for input: \"User@2019@domain.com\"");
		Assert.that(!userEnrollValidator.validateEmail("user#domain.eu"), "Test failed for input: \"user#domain.eu\"");
		Assert.that(!userEnrollValidator.validateEmail("test.eu"), "Test failed for input: \"test.eu\"");
		Assert.that(!userEnrollValidator.validateEmail("test@.eu"), "Test failed for input: \"test@.eu\"");
		Assert.that(!userEnrollValidator.validateEmail("test"), "Test failed for input: \"test\"");
		Assert.that(!userEnrollValidator.validateEmail("@test.eu"), "Test failed for input: \"@test.eu\"");
		Assert.that(!userEnrollValidator.validateEmail("@test"), "Test failed for input: \"@test\"");
		Assert.that(!userEnrollValidator.validateEmail(".com@domain.com"), "Test failed for input: \".com@domain.com\"");
		Assert.that(!userEnrollValidator.validateEmail(".com@domain"), "Test failed for input: \".com@domain\"");
		Assert.that(!userEnrollValidator.validateEmail("user@domain.c"), "Test failed for input: \"user@domain.c\"");
		Assert.that(!userEnrollValidator.validateEmail("user@domain.com."), "Test failed for input: \"user@domain.com.\"");
		Assert.that(!userEnrollValidator.validateEmail("user@domain..com"), "Test failed for input: \"user@domain..com\"");
		Assert.that(!userEnrollValidator.validateEmail("user@domain.corporate"), "Test failed for input: \"user@domain.corporate\"");
		Assert.that(!userEnrollValidator.validateEmail(""), "Test failed for input: \"\"");
		Assert.that(!userEnrollValidator.validateEmail(null), "Test failed for input: null");
	}

	@Test
	public void shouldCorrectlyValidatePassword() {
		Assert.that(userEnrollValidator.validatePassword("Pass123"), "Test failed for input: \"Pass123\"");
		Assert.that(userEnrollValidator.validatePassword("P@ssword123!"), "Test failed for input: \"P@ssword123!\"");
		Assert.that(!userEnrollValidator.validatePassword("pwd"), "Test failed for input: \"pwd\"");
		Assert.that(!userEnrollValidator.validatePassword("pwd12"), "Test failed for input: \"pwd12\"");
		Assert.that(!userEnrollValidator.validatePassword("pass123"), "Test failed for input: \"pass123\"");
		Assert.that(!userEnrollValidator.validatePassword("Pass"), "Test failed for input: \"Pass\"");
		Assert.that(!userEnrollValidator.validatePassword("Password"), "Test failed for input: \"Password\"");
		Assert.that(!userEnrollValidator.validatePassword("123456"), "Test failed for input: \"123456\"");
		Assert.that(!userEnrollValidator.validatePassword("Pwd12"), "Test failed for input: \"Pwd12\"");
		Assert.that(!userEnrollValidator.validatePassword(""), "Test failed for input: \"\"");
		Assert.that(!userEnrollValidator.validatePassword(null), "Test failed for input: null");

	}
}
