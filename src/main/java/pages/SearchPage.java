package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class SearchPage {
    private Page page;

    public SearchPage(Page page) {
        this.page = page;
    }

    public void navigate() {
        page.navigate("http://localhost:3100/search");
    }

    public void enterSearchTerm(String term) {
        page.fill("input[name='searchWord']", term);
    }

    public void submitSearch() {
        page.click("button[type='submit']");
    }

    public String getResultMessage() {
        waitForSearchToFinish(page, 5000);
        return page.textContent("#result");
    }

    public static void waitForSearchToFinish(Page page, int timeout) {
        Locator resultLocator = page.locator("#result");

        // Wait for the text to change from "searching..."
        page.waitForCondition(() -> {
            String textContent = resultLocator.textContent();
            return textContent != null && !textContent.trim().equals("searching...");
        }, new Page.WaitForConditionOptions().setTimeout(timeout));

        System.out.println("Search has finished!");
    }

    public boolean isMessageDisplayed(String message) {
        return page.textContent("#result").contains(message);
    }
}
