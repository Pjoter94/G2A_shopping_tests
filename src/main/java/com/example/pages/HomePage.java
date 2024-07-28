package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class HomePage {
    private final Page page;
    private final Locator searchInputSelector;

    public HomePage(Page page) {
        this.page = page;
        this.searchInputSelector = page.locator("//input[@type='search']");
    }

    public void navigate() {
        page.navigate("https://www.g2a.com");
    }

    public void searchProduct(String productName) {
        searchInputSelector.fill(productName);
        page.keyboard().press("Enter");
        page.waitForLoadState();
    }
}
