# IMPORTANT 
* Tests run only for MAC OSX and Windows
* Tests run only for chrome browser
* chromedriver version supports only chrome browser v81  
# TECH STACK
   *  **Language** : Java
   * **Build management tool** : Maven
   * **Unit testing framework** : TestNG
   * **Reporting** : Allure

# DATA
For Data-driven testng, I have used Data Provider and Factory that is out of the box with TestNG

`com.assignment.data`

is the path where the Data Provider class reside

  
# RUNNING THE TESTS
`mvn clean test` to run the entire suite

`mvn clean test -Dgroups=facebook` to run only the facebook assignment

`mvn clean test -Dgroups=walletHub` to run only the wallet assignment

# REPORTING
* Download the allure command line
    * using npm
    
        `npm install -g allure` 
     
     * using homebrew
     
        `brew install allure`

* Navigate to the root of the project. after running the tests thorugh maven

    `allure serve target/allure-results`
    
* If the test fails, the screenshot will be embedded into the report under a test case     
   