package com.example.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class CartPage extends BasePage {
    private final Page page;
    private final Locator cartTotalPriceSelector;
    public final String cartUrl = "https://www.g2a.com/page/cart";

    public CartPage(Page page) {
        super(page);
        this.page = page;
        this.cartTotalPriceSelector = page.locator("[data-locator='zth-price']");
    }

    public void navigateToCart() {
        page.navigate(cartUrl);
        page.waitForLoadState();
    }

    public String getCartPrice() {
        return cartTotalPriceSelector.nth(0).textContent().strip();
    }

}