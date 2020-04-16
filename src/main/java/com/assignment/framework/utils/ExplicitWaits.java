package com.assignment.framework.utils;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class ExplicitWaits {

    private WebDriver driver;

    public ExplicitWaits(WebDriver driver) {
        this.driver = driver;
    }

    public void explicitWaitVisibilityOfElement(WebElement element, int time) {
        new WebDriverWait(driver, time).until(ExpectedConditions.visibilityOf(element));
    }

    public void waitTillCurrentUrlContains(String url, int time) {
        WebDriverWait wait = new WebDriverWait(driver, time);
        ExpectedCondition<Boolean> urlNotContains = arg0 -> !driver.getCurrentUrl().contains(url);
        wait.until(urlNotContains);
    }

    public void explicitWaitVisibilityOfAllElements(List<WebElement> element, int time) {
        new WebDriverWait(driver, time).until(ExpectedConditions.visibilityOfAllElements(element));
    }

    public void waitForPageLoadToComplete() {
        ExpectedCondition<Boolean> pageLoadCondition = new
                ExpectedCondition<Boolean>() {
                    public Boolean apply(WebDriver driver) {
                        return ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
                    }
                };
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(pageLoadCondition);
    }
}
