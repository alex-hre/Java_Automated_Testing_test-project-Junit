package test;

import model.User;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import page.LoginPage;

import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import service.UserDataProvider;


import java.util.Objects;
import java.util.stream.Stream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

/**
 * This class contains parameterized tests for the login functionality.
 * It tests various login scenarios using JUnit 5's @ParameterizedTest and @MethodSource.
 * The tests run concurrently to improve execution speed.
 */
@Execution(ExecutionMode.CONCURRENT)
public class ParametrizedLoginTest extends BaseTest {


    /**Provides invalid user credentials for positive test cases.*/
    public static Stream<User> wrongUser() {
        return UserDataProvider.getWrongUsers();
    }


    /**Provides valid user credentials for positive test cases.*/
    public static Stream<User> acceptedUser() {
        return UserDataProvider.getAcceptedUsers();
    }


    /**Tests the login form with an empty username and password.*/
    @ParameterizedTest
    @MethodSource("wrongUser")
    public void testLoginEmptyUser(User user) {
        String errorMessage = new LoginPage(getDriver())
                .open()
                .inputFields(user)
                .clearFields(true, true)
                .login()
                .getErrorMessage();

        /*Checking error type*/
        assertThat("Epic sadface: Username is required", equalTo(errorMessage));
    }


    /**Tests the login form with an empty username.*/
    @ParameterizedTest
    @MethodSource("wrongUser")
    public void testLoginEmptyPassword(User user) {
        String errorMessage = new LoginPage(getDriver())
                .open()
                .inputFields(user)
                .clearFields(false, true)
                .login()
                .getErrorMessage();

        /*Checking error type*/
        assertThat("Epic sadface: Password is required", equalTo(errorMessage));
    }


    /**Tests the login form with valid username and password.*/
    @ParameterizedTest
    @MethodSource("acceptedUser")
    public void testSuccessfulLogin(User user) {
        LoginPage loginPage = new LoginPage(getDriver())
                .open()
                .inputFields(user)
                .login();

        assertThat("Login should be successful, but error message appeared!",
                loginPage.isErrorDisplayed(), equalTo(false));

        /*Checking for redirection to the inventory page.*/
        assertThat("User is not redirected to the inventory page!",
                Objects.requireNonNull(getDriver().getCurrentUrl()), equalTo("https://www.saucedemo.com/inventory.html"));
    }

}
