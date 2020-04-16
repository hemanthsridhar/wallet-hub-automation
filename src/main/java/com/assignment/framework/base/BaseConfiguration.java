package com.assignment.framework.base;

import io.qameta.allure.Allure;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.IHookCallBack;
import org.testng.IHookable;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class BaseConfiguration implements IHookable {

    public WebDriver driver;

    @BeforeMethod(alwaysRun = true)
    public void setup() throws Exception {
        try {
            setChromeDriverExecutable();
            ChromeOptions chromeOptions = getChromeOptions();
            driver = new ChromeDriver(chromeOptions);
            driver.manage().window().maximize();
        } catch (NullPointerException e) {
            throw new NullPointerException("Chromedriver executable is not present in the drivers folder. Please check the path");
        }
    }

    private void setChromeDriverExecutable() throws Exception {
        String pathToTheDriverExecutable;
        if (System.getProperty("os.name").toLowerCase().contains("mac".toLowerCase())) {
            pathToTheDriverExecutable = getClass().getClassLoader().getResource("drivers/chromedriver").getPath();
            Runtime.getRuntime().exec("chmod a+x " + pathToTheDriverExecutable);
        } else if (System.getProperty("os.name").toLowerCase().contains("win".toLowerCase())) {
            pathToTheDriverExecutable = "src/test/resources/drivers/chromedriver.exe";
        }
        else {
            pathToTheDriverExecutable = null;
        }
        if(pathToTheDriverExecutable == null){
            throw new Exception("Running automation code in an Unsupported OS");
        }
        System.setProperty("webdriver.chrome.driver", pathToTheDriverExecutable);
    }

    private ChromeOptions getChromeOptions() {
        Map<String, Object> prefs = new HashMap<>();
        //add key and value to map as follow to switch off browser notification
        //Pass the argument 1 to allow ( For me, the browser was asking for allow notifications)
        prefs.put("profile.default_content_setting_values.notifications", 1);
        //Create an instance of ChromeOptions
        ChromeOptions options = new ChromeOptions();
        // set ExperimentalOption - prefs
        options.setExperimentalOption("prefs", prefs);
        return options;
    }

    @AfterMethod(alwaysRun = true)
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Override
    public void run(IHookCallBack iHookCallBack, ITestResult iTestResult) {
        iHookCallBack.runTestMethod(iTestResult);
        if (iTestResult.getThrowable() != null) {
            embedScreenshotToReport();
        }
    }

    private void embedScreenshotToReport() {
        Allure.addAttachment("screenshot", "image/png", new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)), "png");
    }
}
