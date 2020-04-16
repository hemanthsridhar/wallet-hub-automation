package com.assignment.pages.wallethub;

import com.assignment.framework.utils.ExplicitWaits;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class InsuranceReviewPage {

    private WebDriver driver;
    private ExplicitWaits explicitWaits;

    public InsuranceReviewPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        explicitWaits = new ExplicitWaits(this.driver);
    }

    @FindBy(xpath = "//textarea[contains(@placeholder,'Write your review')]")
    private WebElement reviewTextbox;

    @FindBy(xpath = "//span[contains(text(),'Select')]/ancestor::div[contains(@class,'dropdown')]")
    private WebElement insuranceType;

    @FindBy(xpath = "//div[text()='Submit']")
    private WebElement submitButton;

    public InsuranceReviewPage enterAReview(String reviewContent) {
        reviewTextbox.sendKeys(reviewContent);
        return this;
    }

    public InsuranceReviewPage clickOnSubmit() {
        submitButton.click();
        return this;
    }

    public InsuranceReviewPage selectInsuranceType(String insuranceType) throws InterruptedException {
        explicitWaits.explicitWaitVisibilityOfElement(reviewTextbox,5);
        this.insuranceType.click();
        Thread.sleep(1000);
        List<WebElement> insuranceTypes= this.insuranceType.findElement(By.tagName("ul")).findElements(By.tagName("li"));
        for (WebElement insurance : insuranceTypes) {
            if (insurance.getText().trim().equals(insuranceType)) {
                insurance.click();
            }
        }
        return this;
    }
}
