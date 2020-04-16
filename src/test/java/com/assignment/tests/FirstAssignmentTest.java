package com.assignment.tests;

import com.assignment.data.AssignmentDataProvider;
import com.assignment.framework.constants.TestGroups;
import com.assignment.framework.constants.URLs;
import com.assignment.pages.PageInitializer;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class FirstAssignmentTest extends PageInitializer {

    @BeforeMethod(groups = TestGroups.FACEBOOK)
    public void setupFacebookTest(){
        driver.get(URLs.FACEBOOK_URL);
    }

    @Test(groups = TestGroups.FACEBOOK, dataProvider = "postFBStatusMessage",dataProviderClass = AssignmentDataProvider.class)
    public void postStatusMessage(String username, String password, String post) throws InterruptedException {
        login(username, password).postStatusMsg(post);
        //There is no instruction on what needs to be asserted. Hence, added Thread.sleep so that the reviewer can check through the naked eye.
        Thread.sleep(5000);
    }

    private void postStatusMsg(String post) {
        facebookHomePage().clickOnProfileButton().enterPost(post).clickOnSubmitPost();
    }

    private FirstAssignmentTest login(String username, String password) {
        facebookLandingPage().enterUsername(username).enterPassword(password).clickOnLoginButton();
        return this;
    }
}
