package stepDefinitions;

import context.SharedContext;
import io.cucumber.java.en.*;
import org.junit.Assert;
import pages.GridPage;

import static junit.framework.TestCase.assertTrue;

public class GridSteps {
    private GridPage gridPage;

    public GridSteps() {
        this.gridPage = new GridPage(Hooks.getPage());
    }

    @Given("I navigate to the grid page")
    public void navigateToGridPage() {
        gridPage.navigate();
    }

    @Then("the product in position {int} should be {string}")
    public void productInPositionShouldBe(Integer position, String expectedTitle) {
        SharedContext.setPosition(position);
        String actualTitle = gridPage.getProductAtPosition(position);
        Assert.assertEquals(expectedTitle, actualTitle);
    }

    @Then("the price should be {string}")
    public void priceShouldBe(String expectedPrice) {
        int position = SharedContext.getPosition();
        String actualPrice = gridPage.getPriceAtPosition(position);
        Assert.assertEquals(expectedPrice, actualPrice);
    }

    @Then("all items should have a non-empty title, price, image, and a button")
    public void allItemsShouldHaveNonEmptyTitlePriceImageAndButton() {
        boolean allItemsHaveDetails = gridPage.allItemsHaveDetails();
        assertTrue("Not all items have valid details", allItemsHaveDetails);
    }
}
