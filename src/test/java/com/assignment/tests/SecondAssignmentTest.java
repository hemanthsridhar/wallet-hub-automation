package com.assignment.tests;

import com.assignment.data.AssignmentDataProvider;
import com.assignment.framework.constants.TestGroups;
import com.assignment.framework.constants.URLs;
import com.assignment.framework.utils.ExplicitWaits;
import com.assignment.pages.PageInitializer;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class SecondAssignmentTest extends PageInitializer {

    private String username;
    private String password;
    private String insuranceUsername;

    @Factory(dataProvider = "walletHubData", dataProviderClass = AssignmentDataProvider.class)
    public SecondAssignmentTest(String username, String password, String insuranceUsername) {
        this.username = username;
        this.password = password;
        this.insuranceUsername = insuranceUsername;
    }

    @BeforeMethod(groups = TestGroups.WALLET_HUB)
    public void setupWalletTest() throws Exception {
        driver.get(URLs.WALLET_HUB_BASE_URL);
        if (!login(username, password)) {
            throw new Exception("Login Failed. Please check the login method implementation or check the credentials again.");
        }
        driver.get(URLs.WALLET_HUB_PROFILE_BASE_URL + "/" + insuranceUsername);
    }

    @Test(groups = TestGroups.WALLET_HUB, dataProvider = "rateInsurance", dataProviderClass = AssignmentDataProvider.class)
    public void postReview(String reviewerUsername, String companyNameUnderReview, Integer ratingStar, String insuranceType, String reviewContent) throws InterruptedException {
        SoftAssert softAssert = new SoftAssert();
        //check review content length to be greater than or equal to 200
        checkReviewContentLength(200, reviewContent);

        //check if state of the 4th star before hover and after hover are different
        String colorBeforeHover = walletHubInsuranceLandingPage().getColorOfTheStar("before", ratingStar);
        String colorAfterAfterHover = walletHubInsuranceLandingPage().hoverOverRatingBar(ratingStar).getColorOfTheStar("after", ratingStar);
        Assert.assertNotEquals(colorBeforeHover, colorAfterAfterHover, "Color did not change after hovering our the rating star");

       //submit a review for the insurance with type Health insurance
        submitReview(ratingStar, insuranceType, reviewContent);

        //verify if the necessary elements are displayed for recommendation section in profile's page
        boolean isRecommendationSectionDisplayed = walletHubProfilePage(URLs.WALLET_HUB_PROFILE_BASE_URL + "/" + reviewerUsername).verifyIfRecommendationSectionIsDisplayed();

        //verify if the company name under review is displayed
        boolean isCompanyNameUnderReviewDisplayed = walletHubProfilePage().verifyIfReviewIsDisplayed(companyNameUnderReview);

        //assert the entire test and collate the results
        softAssert.assertTrue(isRecommendationSectionDisplayed, "Recommendation section is not displayed in the profile page");
        softAssert.assertTrue(isCompanyNameUnderReviewDisplayed, "Company name for which review was written is not displayed");
        softAssert.assertAll();
    }

    private void checkReviewContentLength(int expectedLength, String reviewContent) {
        if (reviewContent.length() <= expectedLength) {
            Assert.fail("Review Content must be greater than 200");
        }
    }

    private void submitReview(Integer ratingStar, String insuranceType, String reviewContent) throws InterruptedException {
        walletHubInsuranceLandingPage().chooseRating(ratingStar).selectInsuranceType(insuranceType).enterAReview(reviewContent).clickOnSubmit();
        commonsPage().waitForPageLoadToComplete();
        Assert.assertTrue(walletHubInsuranceLandingPage().isDisplayed(), "Review was not submitted. The page did not navigate back to the insurance landing page");
    }

    private boolean login(String username, String password) {
        walletHubLandingPage().clickOnLoginLink().enterUsername(username).enterPassword(password).clickOnLoginButton().waitTillLoginPageDisappears();
        driver.get(URLs.WALLET_HUB_BASE_URL);
        return walletHubHomePage().verifyIfLoggedIn();
    }
}