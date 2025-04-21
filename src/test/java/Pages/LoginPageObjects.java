package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPageObjects {

    WebDriver driver;

    // Constructor
    public void LoginPageObject(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this); // Factory method pattern
    }

    // WebElements using XPath for saucedemo.com
    @FindBy(xpath = "//input[@id='user-name']")
    WebElement usernameInput;

    @FindBy(xpath = "//input[@id='password']")
    WebElement passwordInput;

    @FindBy(xpath = "//input[@id='login-button']")
    WebElement loginButton;

    @FindBy(xpath = "//h3[@data-test='error']")
    WebElement errorMessage;

    // Page Actions
    public void enterUsername(String username) {
        usernameInput.clear();
        usernameInput.sendKeys(username);
    }

    public void enterPassword(String password) {
        passwordInput.clear();
        passwordInput.sendKeys(password);
    }

    public void clickLogin() {
        loginButton.click();
    }



    public String getErrorMessage() {
        return errorMessage.getText();
    }
}

