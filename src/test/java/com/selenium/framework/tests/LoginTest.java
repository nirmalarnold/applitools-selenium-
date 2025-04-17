package com.selenium.framework.tests;

import com.selenium.framework.pages.HomePage;
import com.selenium.framework.pages.LoginPage;
import com.selenium.framework.utils.applitools.ApplitoolsManager;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {

    @Test
    public void testLoginWithValidCredentials() {
        // Setup Applitools visual testing for this test
        setupVisualTesting("Login Test", "My Application");

        LoginPage loginPage = new LoginPage(driver);
        HomePage homePage = new HomePage(driver);

        // Navigate to login page
        loginPage.navigateToLoginPage();

        // Take screenshot of login page with Applitools
        ApplitoolsManager.checkWindow("Login Page");

        // Perform login
        loginPage.enterUsername("test@test.com");
        loginPage.enterPassword("Qwertyuiop1!");
        ApplitoolsManager.checkWindow("Login Form Filled");
        loginPage.clickSignInButton();

        // Verify user is logged in by checking if dashboard is displayed
        Assert.assertTrue(homePage.isUserLoggedIn(), "User should be logged in");

        // Take screenshot of home page after login with Applitools
        ApplitoolsManager.checkWindow("Home Page After Login");

        Assert.assertTrue(homePage.isCollectionTabDisplayed(), "Collection Tab should be displayed after login");
        driver.close();
        driver.quit();
    }

    @Test
    public void testLoginWithInvalidUsername() {
        LoginPage loginPage = new LoginPage(driver);

        // Navigate to login page
        loginPage.navigateToLoginPage();

        // Perform login with invalid username
        loginPage.enterUsername("invalid@test.com");
        loginPage.enterPassword("testing123!");
        loginPage.clickSignInButton();

        // Verify error message is displayed
        Assert.assertTrue(loginPage.isErrorMessageDisplayed(), "Error message should be displayed");
    }

    @Test
    public void testLoginWithInvalidPassword() {
        LoginPage loginPage = new LoginPage(driver);

        // Navigate to login page
        loginPage.navigateToLoginPage();

        // Perform login with invalid password
        loginPage.enterUsername("test@test.com");
        loginPage.enterPassword("wrongpassword");
        loginPage.clickSignInButton();

        // Verify error message is displayed
        Assert.assertTrue(loginPage.isErrorMessageDisplayed(), "Error message should be displayed");
    }
}
