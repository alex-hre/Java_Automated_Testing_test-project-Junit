package test;

import driver.DriverSingleton;
import listener.TestListener;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.WebDriver;

/**
 * Base test class that provides setup and teardown logic for WebDriver.
 * All test classes should extend this class to reuse WebDriver initialization and cleanup.
 */
@ExtendWith(TestListener.class)
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
