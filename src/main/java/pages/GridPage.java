package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

import java.util.List;

public class GridPage {
    private Page page;
    private final Locator gridItems;

    public GridPage(Page page) {
        this.page = page;
        this.gridItems = page.locator(".grid-container .item");
    }

    public void navigate() {
        page.navigate("http://localhost:3100/grid");
    }

    public String getProductAtPosition(int position) {
        Locator product = gridItems.nth(position - 1); // Position starts from 1
        return product.locator("[data-test-id='item-name']").innerText().trim();
    }

    public String getPriceAtPosition(int position) {
        Locator product = gridItems.nth(position - 1);
        return product.locator("#item-price").innerText().trim();
    }

    public boolean allItemsHaveDetails() {
        List<Locator> items = gridItems.all();
        for (Locator item : items) {
            if (item.locator("[data-test-id='item-name']").innerText().trim().isEmpty() ||
                    item.locator("#item-price").innerText().trim().isEmpty() ||
                    item.locator("img").getAttribute("src").isEmpty() ||
                    !item.locator("[data-test-id='add-to-order']").isVisible()) {
                return false;
            }
        }
        return true;
    }
}
