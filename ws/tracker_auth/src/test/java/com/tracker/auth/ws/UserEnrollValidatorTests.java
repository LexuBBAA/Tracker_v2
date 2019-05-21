package com.tracker.auth.ws;

import com.tracker.auth.ws.utils.UserEnrollValidator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserEnrollValidatorTests {

	@Autowired
	private UserEnrollValidator userEnrollValidator;

	@Test
	public void shouldCorrectlyValidateUsername() {
		assertThat("Test failed for input: \"user\"", userEnrollValidator.validateUsername("user"), is(true));
		assertThat("Test failed for input: \"UserTest2019\"", userEnrollValidator.validateUsername("UserTest2019"), is(true));
		assertThat("Test failed for input: \"user_2019\"", userEnrollValidator.validateUsername("user_2019"), is(true));
		assertThat("Test failed for input: \"User_test\"", userEnrollValidator.validateUsername("User_test"), is(true));
		assertThat("Test failed for input: \"User-2019\"", userEnrollValidator.validateUsername("User-2019"), is(true));
		assertThat("Test failed for input: \"User.2019\"", userEnrollValidator.validateUsername("User.2019"), is(true));

		assertThat("Test failed for input: \"User#\"", userEnrollValidator.validateUsername("User#"), is(false));
		assertThat("Test failed for input: \"@Test2019\"", userEnrollValidator.validateUsername("@Test2019"), is(false));
		assertThat("Test failed for input: \".User2019\"", userEnrollValidator.validateUsername(".User2019"), is(false));
		assertThat("Test failed for input: \"UserTest2019.\"", userEnrollValidator.validateUsername("UserTest2019."), is(false));
		assertThat("Test failed for input: \"UserTest2019_\"", userEnrollValidator.validateUsername("UserTest2019_"), is(false));
		assertThat("Test failed for input: \"_UserTest2019\"", userEnrollValidator.validateUsername("_UserTest2019"), is(false));
		assertThat("Test failed for input: \"\"", userEnrollValidator.validateUsername(""), is(false));
		assertThat("Test failed for input: null", userEnrollValidator.validateUsername(null), is(false));
	}

	@Test
	public void shouldCorrectlyValidateEmail() {
		assertThat("Test failed for input: \"user@domain.com\"", userEnrollValidator.validateEmail("user@domain.com"), is(true));
		assertThat("Test failed for input: \"User2019@domain.eu\"", userEnrollValidator.validateEmail("User2019@domain.eu"), is(true));
		assertThat("Test failed for input: \"User2019@domain.eu\"", userEnrollValidator.validateEmail("User_2019@domain.eu"), is(true));
		assertThat("Test failed for input: \"user.2019@domain.eu\"", userEnrollValidator.validateEmail("user.2019@domain.eu"), is(true));
		assertThat("Test failed for input: \"user.test_2019@domain.eu\"", userEnrollValidator.validateEmail("user.test_2019@domain.eu"), is(true));

		assertThat("Test failed for input: \"admin.Backend@tracker_v2.local\"", userEnrollValidator.validateEmail("admin.Backend@tracker_v2.local"), is(false));
		assertThat("Test failed for input: \"User@2019@domain.com\"", userEnrollValidator.validateEmail("User@2019@domain.com"), is(false));
		assertThat("Test failed for input: \"user#domain.eu\"", userEnrollValidator.validateEmail("user#domain.eu"), is(false));
		assertThat("Test failed for input: \"test.eu\"", userEnrollValidator.validateEmail("test.eu"), is(false));
		assertThat("Test failed for input: \"test@.eu\"", userEnrollValidator.validateEmail("test@.eu"), is(false));
		assertThat("Test failed for input: \"test\"", userEnrollValidator.validateEmail("test"), is(false));
		assertThat("Test failed for input: \"@test.eu\"", userEnrollValidator.validateEmail("@test.eu"), is(false));
		assertThat("Test failed for input: \"@test\"", userEnrollValidator.validateEmail("@test"), is(false));
		assertThat("Test failed for input: \".com@domain.com\"", userEnrollValidator.validateEmail(".com@domain.com"), is(false));
		assertThat("Test failed for input: \".com@domain\"", userEnrollValidator.validateEmail(".com@domain"), is(false));
		assertThat("Test failed for input: \"user@domain.c\"", userEnrollValidator.validateEmail("user@domain.c"), is(false));
		assertThat("Test failed for input: \"user@domain.com.\"", userEnrollValidator.validateEmail("user@domain.com."), is(false));
		assertThat("Test failed for input: \"user@domain..com\"", userEnrollValidator.validateEmail("user@domain..com"), is(false));
		assertThat("Test failed for input: \"user@domain.corporate\"", userEnrollValidator.validateEmail("user@domain.corporate"), is(false));
		assertThat("Test failed for input: \"\"", userEnrollValidator.validateEmail(""), is(false));
		assertThat("Test failed for input: null", userEnrollValidator.validateEmail(null), is(false));
	}

	@Test
	public void shouldCorrectlyValidatePassword() {
		assertThat("Test failed for input: \"Pass123\"", userEnrollValidator.validatePassword("Pass123"), is(true));
		assertThat("Test failed for input: \"P@ssword123!\"", userEnrollValidator.validatePassword("P@ssword123!"), is(true));

		assertThat("Test failed for input: \"pwd\"", userEnrollValidator.validatePassword("pwd"), is(false));
		assertThat("Test failed for input: \"pwd12\"", userEnrollValidator.validatePassword("pwd12"), is(false));
		assertThat("Test failed for input: \"pass123\"", userEnrollValidator.validatePassword("pass123"), is(false));
		assertThat("Test failed for input: \"Pass\"", userEnrollValidator.validatePassword("Pass"), is(false));
		assertThat("Test failed for input: \"Password\"", userEnrollValidator.validatePassword("Password"), is(false));
		assertThat("Test failed for input: \"123456\"", userEnrollValidator.validatePassword("123456"), is(false));
		assertThat("Test failed for input: \"Pwd12\"", userEnrollValidator.validatePassword("Pwd12"), is(false));
		assertThat("Test failed for input: \"\"", userEnrollValidator.validatePassword(""), is(false));
		assertThat("Test failed for input: null", userEnrollValidator.validatePassword(null), is(false));
	}
}
