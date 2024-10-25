package stepDefinitions;

import io.cucumber.java.en.*;
import pages.SearchPage;

public class SearchSteps {
    private SearchPage searchPage;

    public SearchSteps() {
        this.searchPage = new SearchPage(Hooks.getPage());
    }

    @Given("I navigate to the search page")
    public void navigateToSearchPage() {
        searchPage.navigate();
    }

    @When("I search for {string}")
    public void searchFor(String searchTerm) {
        searchPage.enterSearchTerm(searchTerm);
        searchPage.submitSearch();
    }

    @When("I leave the search box empty and submit")
    public void leaveSearchBoxEmptyAndSubmit() {
        searchPage.enterSearchTerm("");
        searchPage.submitSearch();
    }

    @Then("I should see {string}")
    public void shouldSeeMessage(String expectedMessage) {
        // Assert that the expected message is displayed
        // implementar que termine el searching...
        String actualMessage = searchPage.getResultMessage();
        assert actualMessage.contains(expectedMessage) : "Expected message: " + expectedMessage + " but got: " + actualMessage;
    }
}
