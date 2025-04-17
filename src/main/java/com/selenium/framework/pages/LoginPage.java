package com.selenium.framework.pages;

import com.selenium.framework.pages.base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {

    @FindBy(id = "username")
    private WebElement usernameField;

    @FindBy(id = "password")
    private WebElement passwordField;

    @FindBy(id = "loginSubmit")
    private WebElement signInButton;

    @FindBy(css = ".error-message")
    private WebElement errorMessage;

    private final String URL = "https://quantum.widencollective.com";

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void navigateToLoginPage() {
        driver.get(URL);
    }

    public void enterUsername(String username) {
        setText(usernameField, username);
    }

    public void enterPassword(String password) {
        setText(passwordField, password);
    }

    public void clickSignInButton() {
        click(signInButton);
    }

    public boolean isErrorMessageDisplayed() {
        return isElementDisplayed(errorMessage);
    }

    public String getErrorMessageText() {
        return getText(errorMessage);
    }

    public void loginToApplication(String username, String password) {
        navigateToLoginPage();
        enterUsername(username);
        enterPassword(password);
        clickSignInButton();
    }
}
