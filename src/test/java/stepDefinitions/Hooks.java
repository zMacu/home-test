package stepDefinitions;

import com.microsoft.playwright.*;
import io.cucumber.java.After;
import io.cucumber.java.Before;

import java.util.Arrays;

public class Hooks {

    private static Playwright playwright;
    private Browser browser;
    private BrowserContext context;
    private static Page page;

    // Retrieve the browser name from system property, default is "chromium"
    private String browserName = System.getProperty("browser", "chromium");

    // Retrieve the headless flag from system property, default is true (headless mode)
    private boolean isHeadless = Boolean.parseBoolean(System.getProperty("headless", "true"));

    @Before
    public void setup() {
        // Initialize Playwright
        playwright = Playwright.create();

        // Print which browser is being launched and its options
        System.out.println("Launching browser: " + browserName + " in headless mode: " + isHeadless);

        // Set launch options with additional debugging
        BrowserType.LaunchOptions launchOptions = new BrowserType.LaunchOptions()
                .setHeadless(isHeadless)
                .setArgs(Arrays.asList("--no-sandbox", "--disable-gpu", "--disable-dev-shm-usage"))
                .setTimeout(30000); // Increase the timeout for browser launch

        // Launch the appropriate browser based on the system property
        switch (browserName.toLowerCase()) {
            case "firefox":
                browser = playwright.firefox().launch(launchOptions);
                break;
            case "chrome":
            case "chromium":
            default:
                browser = playwright.chromium().launch(launchOptions);
                break;
        }

        // Create a new browser context and page
        context = browser.newContext();
        page = context.newPage();
        System.out.println("Page successfully created!");
    }

    @After
    public void tearDown() {
        // Close context and browser
        if (context != null) context.close();
        if (browser != null) browser.close();
        if (playwright != null) playwright.close();
    }

    public static Page getPage() {
        return page;
    }
}
