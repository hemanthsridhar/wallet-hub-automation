package com.assignment.pages.wallethub;

import com.assignment.framework.utils.ExplicitWaits;
import com.assignment.framework.utils.SeleniumUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class ProfilePage {
    private WebDriver driver;
    private ExplicitWaits explicitWaits;
    private SeleniumUtils seleniumUtils;

    public ProfilePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        explicitWaits = new ExplicitWaits(this.driver);
        seleniumUtils = new SeleniumUtils(this.driver);
    }

    public ProfilePage(WebDriver driver, String url) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        explicitWaits = new ExplicitWaits(this.driver);
        driver.get(url);
    }

    String review = "//a[text()='%insurance%']";
    @FindBys(value = {@FindBy(xpath = "//h2[contains(text(),'Recommendations')]"), @FindBy(xpath = "//h2[contains(text(),'I RECOMMEND')]")})
    private WebElement necessaryHeadingsForRecommendations;

    public boolean verifyIfRecommendationSectionIsDisplayed() {
        boolean t;
        try {
            explicitWaits.explicitWaitVisibilityOfElement(necessaryHeadingsForRecommendations, 6);
            t = true;
        }
        catch (TimeoutException e){
            t = false;
        }
        return t;
    }


    public  boolean verifyIfReviewIsDisplayed(String insuranceReviewedFor){
        return seleniumUtils.verifyIfElementIsDisplayed(By.xpath(review.replace("%insurance%",insuranceReviewedFor)));
    }
}
