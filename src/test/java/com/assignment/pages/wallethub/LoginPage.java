package com.assignment.pages.wallethub;

import com.assignment.framework.utils.ExplicitWaits;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
    private WebDriver driver;
    private ExplicitWaits explicitWaits;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        explicitWaits = new ExplicitWaits(this.driver);
    }

    @FindBy(xpath = "//a[text()='Login']")
    private WebElement loginTab;

    @FindBy(name = "em")
    private WebElement emailAddressTextbox;

    @FindBy(name = "pw")
    private WebElement passwordTextbox;

    @FindBy(xpath = "//span[text()='Login']")
    private WebElement loginButton;

    public LoginPage enterUsername(String username) {
        explicitWaits.explicitWaitVisibilityOfElement(emailAddressTextbox, 10);
        emailAddressTextbox.sendKeys(username);
        return this;
    }

    public LoginPage enterPassword(String password) {
        passwordTextbox.sendKeys(password);
        return this;
    }

    public LoginPage clickOnLoginButton() {
        loginButton.click();
        return this;
    }

    public void waitTillLoginPageDisappears() {
        try {
            explicitWaits.waitTillCurrentUrlContains("login", 5);
        } catch (TimeoutException e) {
        }
    }
}
