package com.assignment.pages.wallethub;

import com.assignment.framework.utils.ExplicitWaits;
import com.assignment.framework.utils.SeleniumUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class InsuranceLandingPage {

    private WebDriver driver;
    private ExplicitWaits explicitWaits;
    Actions actions;
    SeleniumUtils seleniumUtils;

    public InsuranceLandingPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        explicitWaits = new ExplicitWaits(this.driver);
        seleniumUtils = new SeleniumUtils(this.driver);
        actions = new Actions(this.driver);
    }

    @FindBy(css = "div[class='review-action ng-enter-element'] div[class='rating-box-wrapper'] svg g path")
    private List<WebElement> whatsYourRatingStars;

    @FindBy(xpath = "//button[text()='Write a Review']")
    private WebElement writeAReviewButton;

    public InsuranceLandingPage hoverOverRatingBar(Integer ratingStar) {
        explicitWaits.explicitWaitVisibilityOfElement(whatsYourRatingStars.get(ratingStar - 1),10);
        actions.moveToElement(whatsYourRatingStars.get(ratingStar - 1)).build().perform();
        return this;
    }

    public InsuranceReviewPage chooseRating(Integer ratingStar) {
        actions.moveToElement(whatsYourRatingStars.get((whatsYourRatingStars.size() - ratingStar) + 1)).click().build().perform();
        return new InsuranceReviewPage(driver);
    }

    public String getColorOfTheStar(String beforeOrAfterHover, Integer ratingStar) {
        if ("after".equals(beforeOrAfterHover)) {
            return whatsYourRatingStars.get((whatsYourRatingStars.size() - ratingStar) + 1).getAttribute("d").trim();
        }
        return whatsYourRatingStars.get(ratingStar - 1).getAttribute("d").trim();
    }

    public boolean isDisplayed() {
        return seleniumUtils.verifyIfElementIsDisplayed(writeAReviewButton, 4);
    }
}
