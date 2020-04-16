package com.assignment.pages.facebook;

import com.assignment.framework.utils.ExplicitWaits;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class FacebookWallPage {

    private WebDriver driver;
    private ExplicitWaits explicitWaits;

    public FacebookWallPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        explicitWaits = new ExplicitWaits(this.driver);
    }

    @FindBy(xpath = "//span[text()='Create Post']")
    private WebElement createAPostButton;

    @FindBy(xpath = "//div[@aria-label='Create a post']/descendant::div[@role='textbox']")
    private WebElement postTextbox;

    @FindBy(xpath = "//span[text()='Post']")
    private WebElement submitPostButton;


    public FacebookWallPage enterPost(String post) {
        explicitWaits.explicitWaitVisibilityOfElement(this.createAPostButton, 20);
        createAPostButton.click();
        explicitWaits.explicitWaitVisibilityOfElement(postTextbox, 5);
        postTextbox.sendKeys(post);
        return this;
    }

    public FacebookWallPage clickOnSubmitPost() {
        submitPostButton.click();
        return this;
    }
}
