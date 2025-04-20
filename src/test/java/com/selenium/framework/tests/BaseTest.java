package com.selenium.framework.tests;

import com.selenium.framework.utils.DriverManager;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

public class BaseTest {

    protected WebDriver driver;

    @BeforeClass
    public void setupClass() {
        // Class setup if needed
    }

    @BeforeMethod
    @Parameters({ "browser" })
    public void setUp(@Optional("chrome") String browser) {
        DriverManager.initDriver(browser);
        driver = DriverManager.getDriver();
    }

    @AfterMethod
    public void tearDown() {
        DriverManager.quitDriver();
    }

    @AfterClass
    public void tearDownClass() {
        // Class cleanup if needed
    }
}
