package com.assignment.pages;

import com.assignment.framework.base.BaseConfiguration;
import com.assignment.pages.facebook.FacebookHomePage;
import com.assignment.pages.facebook.FacebookLandingPage;
import com.assignment.pages.wallethub.*;

public class PageInitializer extends BaseConfiguration {

    public FacebookLandingPage facebookLandingPage() {
        return new FacebookLandingPage(driver);
    }

    public FacebookHomePage facebookHomePage() {
        return new FacebookHomePage(driver);
    }

    public HomePage walletHubHomePage() {
        return new HomePage(driver);
    }

    public InsuranceLandingPage walletHubInsuranceLandingPage() {
        return new InsuranceLandingPage(driver);
    }

    public InsuranceReviewPage walletHubInsuranceReviewPage() {
        return new InsuranceReviewPage(driver);
    }

    public LoginPage walletHubLoginPage() {
        return new LoginPage(driver);
    }

    public ProfilePage walletHubProfilePage() {
        return new ProfilePage(driver);
    }

    public ProfilePage walletHubProfilePage(String url) {
        return new ProfilePage(driver,url);
    }

    public LandingPage walletHubLandingPage() {
        return new LandingPage(driver);
    }

    public CommonsPage commonsPage() {
        return new CommonsPage(driver);
    }

}
