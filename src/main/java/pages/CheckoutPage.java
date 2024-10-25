package pages;

import com.microsoft.playwright.Page;

import java.util.List;

public class CheckoutPage {
    private Page page;

    public CheckoutPage(Page page) {
        this.page = page;
    }

    public void navigate() {
        page.navigate("http://localhost:3100/checkout");
    }

    public void fillCheckoutForm(String name, String email, String address, String city, String state, String zip) {
        page.fill("#fname", name);
        page.fill("#email", email);
        page.fill("#adr", address);
        page.fill("#city", city);
        page.fill("#state", state);
        page.fill("#zip", zip);
    }

    public void fillCheckoutCardForm(String name, String cardNumber, String month, String expYear, String CVV) {
        page.fill("#cname", name);
        page.fill("#ccnum", cardNumber);
        selectExpMonth(month);
        page.fill("#expyear", expYear);
        page.fill("#cvv", CVV);
    }

    public void selectExpMonth(String month) {
        page.selectOption("#expmonth", month);
    }

    public void checkShippingAddressCheckbox() {
        page.check("input[name='sameadr']");
    }

    public void uncheckShippingAddressCheckbox() {
        page.uncheck("input[name='sameadr']");
    }

    public void submitForm() {
        page.click("button:has-text('Continue to checkout')");
    }

    public String getOrderConfirmationNumber() {
        return page.textContent("#order-confirmation");
    }

    public String getCartTotal() {
        return page.textContent("div.container > p:has-text('Total') > .price").trim();
    }

    public List<String> getProductPrices() {
        // Locate the product prices before the Total line (ignores the final total price)
        return page.locator("div.container p:not(:has-text('Total')) .price").allTextContents();
    }

    public double calculateProductTotal() {
        List<String> prices = getProductPrices();
        return prices.stream()
                .mapToDouble(price -> Double.parseDouble(price.replace("$", "")))
                .sum();
    }

}
