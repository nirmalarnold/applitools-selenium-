package com.selenium.framework.tests;

import com.selenium.framework.utils.DriverManager;
import com.selenium.framework.utils.applitools.ApplitoolsManager;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

public class BaseTest {

    protected WebDriver driver;
    protected boolean useEyesVisualTesting = false;

    @BeforeClass
    public void setupClass() {
        // Initialize Applitools Eyes
        ApplitoolsManager.initializeEyes();
    }

    @BeforeMethod
    @Parameters({ "browser" })
    public void setUp(@Optional("chrome") String browser) {
        DriverManager.initDriver(browser);
        driver = DriverManager.getDriver();
    }

    @AfterMethod
    public void tearDown() {
        // Close Eyes if using visual testing
        if (useEyesVisualTesting) {
            ApplitoolsManager.closeEyes();
        }
        DriverManager.quitDriver();
    }

    @AfterClass
    public void tearDownClass() {
        // Get all Applitools test results
        ApplitoolsManager.getAllTestResults();
    }

    protected void setupVisualTesting(String testName, String appName) {
        useEyesVisualTesting = true;
        driver = ApplitoolsManager.openEyes(driver, testName, appName);
    }
}
