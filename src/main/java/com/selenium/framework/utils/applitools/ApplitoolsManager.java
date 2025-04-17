package com.selenium.framework.utils.applitools;

import com.applitools.eyes.BatchInfo;
import com.applitools.eyes.EyesRunner;
import com.applitools.eyes.selenium.BrowserType;
import com.applitools.eyes.selenium.ClassicRunner;
import com.applitools.eyes.selenium.Configuration;
import com.applitools.eyes.selenium.Eyes;
import com.applitools.eyes.visualgrid.services.RunnerOptions;
import com.applitools.eyes.visualgrid.services.VisualGridRunner;
import org.openqa.selenium.WebDriver;

public class ApplitoolsManager {
    private static final String API_KEY = System.getenv("APPLITOOLS_API_KEY");
    private static ThreadLocal<Eyes> eyes = new ThreadLocal<>();
    private static ThreadLocal<EyesRunner> runner = new ThreadLocal<>();
    private static ThreadLocal<BatchInfo> batch = new ThreadLocal<>();

    private static final boolean USE_VISUAL_GRID = false; // Set to true to use Ultrafast Grid

    // Initialize Applitools for a test
    public static void initializeEyes() {
        if (USE_VISUAL_GRID) {
            // Initialize the eyes runner with concurrency of 5
            runner.set(new VisualGridRunner(new RunnerOptions().testConcurrency(5)));
        } else {
            // Initialize the classic runner
            runner.set(new ClassicRunner());
        }

        // Initialize Eyes
        eyes.set(new Eyes(runner.get()));

        // Set API key
        eyes.get().setApiKey(API_KEY);

        // Create batch if not exists
        if (batch.get() == null) {
            batch.set(new BatchInfo("Selenium Framework Tests"));
        }

        // Set batch for this test
        eyes.get().setBatch(batch.get());
    }

    // Open Eyes for a test
    public static WebDriver openEyes(WebDriver driver, String testName, String appName) {
        // Configure eyes
        Configuration config = new Configuration();
        config.setAppName(appName);
        config.setTestName(testName);

        if (USE_VISUAL_GRID) {
            // Configure browsers for Ultrafast Grid
            config.addBrowser(1200, 800, BrowserType.CHROME);
            config.addBrowser(1200, 800, BrowserType.FIREFOX);
            config.addBrowser(1200, 800, BrowserType.SAFARI);
            config.addBrowser(1200, 800, BrowserType.EDGE_CHROMIUM);
        }

        eyes.get().setConfiguration(config);

        // Open eyes
        return eyes.get().open(driver);
    }

    // Check the window with a tag
    public static void checkWindow(String tag) {
        eyes.get().checkWindow(tag);
    }

    // Close eyes for the test
    public static void closeEyes() {
        if (eyes.get() != null) {
            try {
                eyes.get().closeAsync();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    // Get Applitools test results
    public static void getAllTestResults() {
        if (runner.get() != null) {
            try {
                runner.get().getAllTestResults();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                runner.remove();
                eyes.remove();
            }
        }
    }
}