package com.assignment.pages.facebook;

import com.assignment.framework.utils.ExplicitWaits;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class FacebookLandingPage {

    private WebDriver driver;
    private ExplicitWaits explicitWaits;

    public FacebookLandingPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        explicitWaits = new ExplicitWaits(this.driver);
    }

    @FindBy(id = "email")
    private WebElement username;

    @FindBy(id = "pass")
    private WebElement password;

    @FindBy(id = "loginbutton")
    private WebElement loginButton;

    public FacebookLandingPage enterUsername(String username) {
        explicitWaits.explicitWaitVisibilityOfElement(this.username, 5);
        this.username.sendKeys(username);
        return this;
    }

    public FacebookLandingPage enterPassword(String password) {
        this.password.sendKeys(password);
        return this;
    }

    public FacebookLandingPage clickOnLoginButton() {
        loginButton.click();
        return this;
    }
}
