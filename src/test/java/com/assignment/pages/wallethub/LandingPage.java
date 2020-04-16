package com.assignment.pages.wallethub;

import com.assignment.framework.utils.ExplicitWaits;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LandingPage {

    private WebDriver driver;
    private ExplicitWaits explicitWaits;

    public LandingPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        explicitWaits = new ExplicitWaits(this.driver);
    }

    @FindBy(css = "a[class='login']")
    private WebElement loginLink;


    public LoginPage clickOnLoginLink(){
        loginLink.click();
        return new LoginPage(driver);
    }
}
