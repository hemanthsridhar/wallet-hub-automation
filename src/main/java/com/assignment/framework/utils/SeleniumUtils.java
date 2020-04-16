package com.assignment.framework.utils;

import org.openqa.selenium.*;

public class SeleniumUtils {

    private WebDriver driver;

    public SeleniumUtils(WebDriver driver) {
        this.driver = driver;
    }

    public boolean verifyIfElementIsDisplayed(By element) {
        try {
            return driver.findElement(element).isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public boolean verifyIfElementIsDisplayed(WebElement element) {
        try {
            return element.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public boolean verifyIfElementIsDisplayed(WebElement element, int time) {
        try {
            new ExplicitWaits(driver).explicitWaitVisibilityOfElement(element,time);
            return element.isDisplayed();
        } catch (TimeoutException | NoSuchElementException e) {
            return false;
        }
    }

}
