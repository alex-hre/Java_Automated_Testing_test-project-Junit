package driver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DriverSingleton {

    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    private DriverSingleton() {}

    /**Creating driver*/
    public static WebDriver getDriver() {

        /*If driver is not created - creating new driver*/
        if (driver.get() == null) {
            String browser = System.getProperty("browser", "chrome"); // default driver is Chrome
            System.out.println("Browser: " + browser);

            switch (browser.toLowerCase()) {
                case "firefox":
                    WebDriverManager.firefoxdriver().setup();
                    driver.set(new FirefoxDriver());
                    break;

                case "edge":
                    WebDriverManager.edgedriver().setup();
                    driver.set(new EdgeDriver());
                    break;

                case "chrome":
                default:
                    WebDriverManager.chromedriver().setup();
                    driver.set(new org.openqa.selenium.chrome.ChromeDriver());
            }

            driver.get().manage().window().maximize();
        }
        return driver.get();
    }

    /**Closing driver*/
    public static void closeDriver(){
        if (driver.get() != null) {
            driver.get().quit();
            driver.remove();
        }
    }
}
