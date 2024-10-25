package stepDefinitions;

import io.cucumber.java.en.*;
import pages.CheckoutPage;
import static org.junit.jupiter.api.Assertions.*;

public class CheckoutSteps {
    private CheckoutPage checkoutPage;
    private String alertMessage;

    public CheckoutSteps() {
        this.checkoutPage = new CheckoutPage(Hooks.getPage());

        // Add a dialog listener to handle alert messages
        Hooks.getPage().onDialog(dialog -> {
            alertMessage = dialog.message(); // Capture the alert message
            dialog.accept(); // Accept the alert
        });
    }

    @Given("I navigate to the checkout page")
    public void navigateToCheckoutPage() {
        checkoutPage.navigate();
    }

    @When("I fill in the checkout form with valid details")
    public void fillInCheckoutForm() {
        checkoutPage.fillCheckoutForm("John Doe", "Jogndoe@gmail.com", "123 Main St", "Anytown", "New York","12345");
        checkoutPage.fillCheckoutCardForm("John Doe", "4242424242242", "June", "2027", "123");
    }

    @When("I check the {string} checkbox")
    public void checkShippingAddressCheckbox(String checkbox) {
        if (checkbox.equals("Shipping address same as billing")) {
            checkoutPage.checkShippingAddressCheckbox();
        }
    }

    @When("I uncheck the {string} checkbox")
    public void uncheckShippingAddressCheckbox(String checkbox) {
        if (checkbox.equals("Shipping address same as billing")) {
            checkoutPage.uncheckShippingAddressCheckbox();
        }
    }

    @When("I submit the checkout form")
    @When("I try to submit the checkout form")
    public void submitCheckoutForm(){
        checkoutPage.submitForm();
    }

    @Then("the order confirmation number should not be empty")
    public void verifyOrderConfirmationNumber() {
        String confirmationNumber = checkoutPage.getOrderConfirmationNumber();
        assertNotNull(confirmationNumber);
        assertFalse(confirmationNumber.isEmpty());
    }

    @Then("I should see an alert message")
    public void verifyAlertMessage() {
        // Wait for the dialog to appear before asserting (doing this since waitForEvent is not available)
        Hooks.getPage().waitForTimeout(1000);
        assertNotNull(alertMessage);
        assertFalse(alertMessage.isEmpty());
        System.out.println("Alert message: " + alertMessage);
    }

    @Then("the alert message should be gone after confirmation")
    public void verifyAlertGone() {
        // Initialize a flag to track whether a second dialog appears
        final boolean[] newDialogAppeared = {false};

        // Add another listener to track if any new dialogs appear
        Hooks.getPage().onDialog(dialog -> {
            newDialogAppeared[0] = true;
            dialog.accept(); // Accept any unexpected dialog
        });

        // Wait for 2 seconds to check if any new dialog appears (doing this since waitForEvent is not available)
        Hooks.getPage().waitForTimeout(2000);

        // Assert that no new dialogs appeared
        assertFalse(newDialogAppeared[0], "A new alert appeared after accepting the first one.");
    }

    @Then("the cart total should match the item prices added")
    public void verifyCartTotal() {
        String cartTotal = checkoutPage.getCartTotal();
        double calculatedTotal = checkoutPage.calculateProductTotal();

        // Format the calculated total to match the format on the page
        String formattedTotal = String.format("$%.2f", calculatedTotal);

        // Remove trailing zeros if present for proper comparison
        if (formattedTotal.endsWith(".00")) {
            formattedTotal = formattedTotal.substring(0, formattedTotal.length() - 3);
        }

        assertEquals(formattedTotal, cartTotal);
    }

}
