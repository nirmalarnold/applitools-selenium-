package com.selenium.framework.tests;

import com.selenium.framework.pages.HomePage;
import com.selenium.framework.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {

    @Test
    public void testLoginWithValidCredentials() {

        LoginPage loginPage = new LoginPage(driver);
        HomePage homePage = new HomePage(driver);

        // Navigate to login page
        loginPage.navigateToLoginPage();

        // Perform login
        loginPage.enterUsername("test@test.com");
        loginPage.enterPassword("Qwertyuiop1!");
        loginPage.clickSignInButton();

        // Verify user is logged in by checking if dashboard is displayed
        Assert.assertTrue(homePage.isUserLoggedIn(), "User should be logged in");

        Assert.assertTrue(homePage.isCollectionTabDisplayed(), "Collection Tab should be displayed after login");
        // Removing redundant driver close and quit commands as they're already handled
        // in tearDown()
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
        Assert.assertTrue(loginPage.isErrorMessageDisplayed(), "Your email address or password was incorrect.");
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
        Assert.assertTrue(loginPage.isErrorMessageDisplayed(), "Your email address or password was incorrect.");
    }
}
