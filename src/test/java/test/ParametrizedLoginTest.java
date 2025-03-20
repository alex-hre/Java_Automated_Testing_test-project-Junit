package test;

import model.User;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import page.LoginPage;
import service.UserDataProvider;

import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

@Execution(ExecutionMode.CONCURRENT)
public class ParametrizedLoginTest extends BaseTest {

    public static Stream<User> wrongUser() {
        return UserDataProvider.getWrongUsers();
    }

    public static Stream<User> acceptedUser() {
        return UserDataProvider.getAcceptedUsers();
    }

    @ParameterizedTest
    @MethodSource("wrongUser")
    public void testLoginEmptyUser(User user) {
        String errorMessage = new LoginPage(getDriver())
                .open()
                .inputFields(user)
                .clearFields(true, true)
                .login()
                .getErrorMessage();

        assertThat(errorMessage)
                .as("Expected error message for empty username")
                .isEqualTo("Epic sadface: Username is required");
    }

    @ParameterizedTest
    @MethodSource("wrongUser")
    public void testLoginEmptyPassword(User user) {
        String errorMessage = new LoginPage(getDriver())
                .open()
                .inputFields(user)
                .clearFields(false, true)
                .login()
                .getErrorMessage();

        assertThat(errorMessage)
                .as("Expected error message for empty password")
                .isEqualTo("Epic sadface: Password is required");
    }

    @ParameterizedTest
    @MethodSource("acceptedUser")
    public void testSuccessfulLogin(User user) {
        LoginPage loginPage = new LoginPage(getDriver())
                .open()
                .inputFields(user)
                .login();

        assertThat(loginPage.isErrorDisplayed())
                .as("Login should be successful, but error message appeared!")
                .isFalse();

        assertThat(getDriver().getCurrentUrl())
                .as("User is not redirected to the inventory page!")
                .isEqualTo("https://www.saucedemo.com/inventory.html");
    }
}
