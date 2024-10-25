package stepDefinitions;

import io.cucumber.java.en.*;
import pages.LoginPage;
import static org.junit.jupiter.api.Assertions.*;

public class LoginSteps {
    private LoginPage loginPage;

    public LoginSteps() {
        this.loginPage = new LoginPage(Hooks.getPage());
    }

    @Given("I am on the login page")
    public void navigateToLoginPage() {
        loginPage.navigate();
    }

    @When("I login with username {string} and password {string}")
    public void login(String username, String password) {
        loginPage.login(username, password);
    }

    @When("I click on login button")
    public void login() {
        loginPage.clickLoginButton();
    }

    @Then("I should see the welcome message with username {string}")
    public void verifyWelcomeMessage(String username) {
        assertTrue(loginPage.getWelcomeMessage().contains(username));
    }

    @Then("I should see an error message {string}")
    public void verifyErrorMessage(String message) {
        Hooks.getPage().waitForTimeout(1000); //(doing this since waitForEvent is not available)
        assertFalse(loginPage.getErrorMessage().isEmpty());
        assertTrue(loginPage.getErrorMessage().matches(message));
    }
}
