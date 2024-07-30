package com.example.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class ProductPage extends BasePage {
    private final Page page;
    private final Locator priceSelector;
    private final Locator addToCartButtonSelector;

    public ProductPage(Page page) {
        super(page);
        this.page = page;
        this.priceSelector = page.locator("[data-locator='ppa-payment']");
        this.addToCartButtonSelector = page.locator("[data-locator='ppa-payment__btn']");


    }

    public String getProductPrice() {
        return priceSelector.first().textContent().strip();
    }

    public void addToCart() {
        addToCartButtonSelector.nth(0).click();
        page.waitForLoadState();
    }

}
