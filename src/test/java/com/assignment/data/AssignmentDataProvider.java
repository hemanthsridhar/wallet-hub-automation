package com.assignment.data;

import org.apache.commons.lang3.RandomStringUtils;
import org.testng.annotations.DataProvider;

public class AssignmentDataProvider {

    /**
     *
     * Facebook EmailId
     * Facebook Password
     * Content to Post
     *
     */
    @DataProvider(name = "postFBStatusMessage")
    public Object[][] postFBStatusMessage() {
        return new Object[][]{{"emailId", "password", "Hello World"}};
    }

    /**
     * username of the reviewer
     * Insurance name under review
     * Number of stars for the review
     * Insurance type related to the insurance under review
     * Review Content
     */
    @DataProvider(name = "rateInsurance")
    public Object[][] rateInsurance() {
        return new Object[][]{{
                "hemanthsridhar",
                "Test Insurance Company",
                4,
                "Health Insurance",
                RandomStringUtils.randomAlphabetic(501)
        }
        };
    }

    /**
     *
     * Wallet Hub username
     * Wallet Hub Password
     * Username of the insurance under review
     */
    @DataProvider(name = "walletHubData")
    public Object[][] walletHubData() {
        return new Object[][]{{"emailId", "password", "test_insurance_company"}};
    }
}
