package listener;

import driver.DriverSingleton;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestWatcher;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TestListener implements TestWatcher {

    @Override
    public void testFailed(ExtensionContext context, Throwable cause) {
        takeScreenshot(context.getDisplayName());
    }

    private void takeScreenshot(String testName) {
        if (DriverSingleton.getDriver() == null) {
            return;
        }
        File screenshot = ((TakesScreenshot) DriverSingleton.getDriver()).getScreenshotAs(OutputType.FILE);
        String timestamp = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(new Date());
        String filename = "screenshots/" + testName.replace(" ", "_") + "_" + timestamp + ".png";
        try {
            FileUtils.copyFile(screenshot, new File(filename));
            System.out.println("🛑 Screenshot saved: " + filename);
        } catch (IOException e) {
            System.err.println("❌ Failed to save screenshot: " + e.getMessage());
        }
    }
}