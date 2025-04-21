package stepDef;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import Pages.LoginPageObjects;
import utility.BrowserDriver;
import utility.JSONReader;
import java.util.List;
import java.util.Map;

public class UserLogin {
    private BrowserDriver browserDriver;
    private LoginPageObjects loginPage;
    private List<Map<String, String>> testData;
    private int currentIndex = 0;

    @Given("I am on the login page")
    public void i_am_on_the_login_page() {
        browserDriver = new BrowserDriver();
        loginPage = new LoginPageObjects();
        loginPage.LoginPageObject(browserDriver.getDriver());
        browserDriver.getDriver().get("https://www.saucedemo.com/");
    }

    @When("I enter credential {string}")
    public void i_enter_credential(String credentialType) {
        // Initialize test data if not already initialized
        if (testData == null) {
            JSONReader jsonReader = new JSONReader("valid_logins");
            testData = jsonReader.getAllTestData();
        }
        
        // Get the current test case
        Map<String, String> currentData = testData.get(currentIndex);
        String username = currentData.get("username");
        String password = currentData.get("password");
        
        System.out.println("Testing with username: " + username);
        loginPage.enterUsername(username);
        loginPage.enterPassword(password);
    }

    @When("I click the login button")
    public void i_click_the_login_button() {
        loginPage.clickLogin();
    }

    @Then("I should see the appropriate response")
    public void i_should_see_the_appropriate_response() {
        String expectedUrl = "https://www.saucedemo.com/inventory.html";
        String currentUrl = browserDriver.getDriver().getCurrentUrl();
        
        if (currentUrl.equals(expectedUrl)) {
            Assert.assertEquals("Login successful", expectedUrl, currentUrl);
        } else {
            String errorMessage = loginPage.getErrorMessage();
            Assert.assertNotNull("Error message should be displayed", errorMessage);
        }
        
        // Move to next test case
        currentIndex++;
        if (currentIndex >= testData.size()) {
            currentIndex = 0;
        }
        
        browserDriver.close();
    }
}
