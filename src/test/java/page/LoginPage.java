package page;

import model.User;
import org.openqa.selenium.Keys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


/**
 * Page Object class representing the Login Page of the Saucedemo website.
 * Provides methods to interact with login elements and perform login actions.
 */
public class LoginPage {

    private WebDriver driver;

    /**
     * Web elements for login form fields and buttons.
     * Uses @FindBy annotation for locating elements via XPath.
     */

    @FindBy(css = "input[data-test='username']")
    private WebElement usernameInput;

    @FindBy(css = "input[data-test='password']")
    private WebElement passwordInput;

    @FindBy(css = "input[data-test='login-button']")
    private WebElement loginButton;

    @FindBy(css = ".error-message-container") // Локатор ошибки
    private WebElement errorMessage;

    @FindBy(css = "div.app_logo")
    private WebElement siteTitle;





    /**Logger instance for logging actions*/
    private static final Logger logger = LoggerFactory.getLogger(LoginPage.class);


    /**
     * Constructor to initialize the LoginPage with WebDriver.
     * @param driver WebDriver instance used for interacting with the web page.
     */
    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }


    /**
     * Opens the login page by navigating to the Saucedemo URL.
     * returns the current instance of LoginPage for method chaining.
     */
    public LoginPage open() {
        driver.get("https://www.saucedemo.com/");
        return this;
    }


    public LoginPage inputFields(User user) {
        usernameInput.sendKeys(user.getUsername());
        passwordInput.sendKeys(user.getPassword());

        return this;
    }


    public void clearUsername() {
        usernameInput.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.BACK_SPACE));
        logger.info("Cleared username field");
    }


    public void clearPassword() {
        passwordInput.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.BACK_SPACE));
        logger.info("Cleared password field");
    }

    /**Clearing username and/or password*/
    public LoginPage clearFields(boolean clearUsername, boolean clearPassword) {
        if (clearUsername) {
            clearUsername();
        }
        if (clearPassword) {
            clearPassword();
        }
        return this;
    }

    /**Click on the "Login" button and check for error messages*/
    public LoginPage login() {
        loginButton.click();
        logger.info("Clicked Login button");

        if (isErrorDisplayed()) {
            logger.warn("Login failed. Error message: {}", getErrorMessage());
        } else {
            logger.info("Login successful");
        }

        return this;
    }

    /**Catching error messages*/
    public boolean isErrorDisplayed() {
        try {
            return errorMessage.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }


    public String getErrorMessage() {
        return errorMessage.getText();
    }

}