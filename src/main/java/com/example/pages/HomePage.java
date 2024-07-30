package com.example.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class HomePage extends BasePage {
    private final Page page;
    private final Locator searchInputSelector;
    private final Locator acceptCookieButton;
    public final String homeUrl = "https://www.g2a.com/";

    public HomePage(Page page) {
        super(page);
        this.page = page;
        this.searchInputSelector = page.locator("//input[@type='search']");
        this.acceptCookieButton = page.locator("[data-test-id='cookie-accept-all-btn']");
    }

    public void navigate() {
        page.navigate(homeUrl);
    }

    public void searchProduct(String productName) {
        searchInputSelector.fill(productName);
        page.keyboard().press("Enter");
        page.waitForLoadState();
    }

    public void acceptCookie(){
        acceptCookieButton.click();
    }
}
