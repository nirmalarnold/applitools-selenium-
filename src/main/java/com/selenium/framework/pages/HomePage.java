package com.selenium.framework.pages;

import com.selenium.framework.pages.base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage {

    @FindBy(xpath = "//*[@data-testid = 'p-avatar-initials']")
    private WebElement userProfileMenu;

    @FindBy(xpath = "//*[text()='Collections']")
    private WebElement collectionTab;

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public boolean isUserLoggedIn() {
        return isElementDisplayed(userProfileMenu);
    }

    public boolean isCollectionTabDisplayed() {
        return isElementDisplayed(collectionTab);
    }

    public String getCollectionTab() {
        return getText(collectionTab);
    }
}
