package com.assignment.pages.facebook;

import com.assignment.framework.utils.ExplicitWaits;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class FacebookHomePage {

    private WebDriver driver;
    private ExplicitWaits explicitWaits;

    public FacebookHomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        explicitWaits = new ExplicitWaits(this.driver);
    }

    @FindBy(css = "a[title='Profile']")
    private WebElement profileButton;


    public FacebookWallPage clickOnProfileButton() {
        explicitWaits.explicitWaitVisibilityOfElement(profileButton,10);
        profileButton.click();
        return new FacebookWallPage(driver);
    }
}
