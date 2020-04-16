package com.assignment.pages;

import com.assignment.framework.utils.ExplicitWaits;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class CommonsPage {

    private WebDriver driver;
    private ExplicitWaits explicitWaits;

    public CommonsPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        explicitWaits = new ExplicitWaits(this.driver);
    }

    public void waitForPageLoadToComplete() {
        explicitWaits.waitForPageLoadToComplete();
    }
}
