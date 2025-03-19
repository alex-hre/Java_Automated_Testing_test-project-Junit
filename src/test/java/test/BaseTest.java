package test;

import driver.DriverSingleton;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;

/**
 * Base test class that provides setup and teardown logic for WebDriver.
 * All test classes should extend this class to reuse WebDriver initialization and cleanup.
 */
public abstract class BaseTest {
    protected WebDriver driver;

    /**DInitializing Driver*/
    @BeforeEach
    void setUp() {
        driver = DriverSingleton.getDriver();
    }

    /**Closing Driver*/
    @AfterEach
    void tearDown() {
        DriverSingleton.closeDriver();
    }

    protected WebDriver getDriver() {
        return driver;
    }
}
