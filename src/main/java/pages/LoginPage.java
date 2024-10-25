package pages;

import com.microsoft.playwright.Page;

public class LoginPage {
    private Page page;

    public LoginPage(Page page) {
        this.page = page;
    }

    public void navigate() {
        page.navigate("http://localhost:3100/login");
    }

    public void login(String username, String password) {
        page.fill("#username", username);
        page.fill("#password", password);
        clickLoginButton();
    }

    public void clickLoginButton() {
        page.click("#signin-button");
    }

    public String getWelcomeMessage() {
        return page.textContent("#welcome-message");
    }

    public String getErrorMessage() {
        return page.textContent("#message");
    }
}
