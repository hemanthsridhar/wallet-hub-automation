package com.assignment.pages.wallethub;

import com.assignment.framework.utils.ExplicitWaits;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {

    private WebDriver driver;
    private ExplicitWaits explicitWaits;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        explicitWaits = new ExplicitWaits(this.driver);
    }

    @FindBy(css = "a[class='user']")
    private WebElement userProfileTab;

    @FindBy(css = "div[class='logo'] > a[href='/home/dashboard/']")
    private WebElement logo;

    public HomePage clickOnLogo(){
        explicitWaits.explicitWaitVisibilityOfElement(logo, 5);
        logo.click();
        return this;
    }
    public boolean verifyIfLoggedIn() {
        try {
            explicitWaits.explicitWaitVisibilityOfElement(userProfileTab, 10);
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }
}
