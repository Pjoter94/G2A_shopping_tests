package com.example.pages;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class SearchPage extends BasePage {
    private final Page page;
    private final Locator productList;
    public final String searchProductUrl = "https://www.g2a.com/search?query=";

    public SearchPage(Page page){
        super(page);
        this.page = page;
        this.productList = page.locator("(//li[contains(@class,'sc-gIvpjk kgKIqe')])");
    }

    public void navigateToSearchProduct(String productName) {
        page.navigate(searchProductUrl + productName);
        page.waitForLoadState();
    }

    public void selectProduct(int productIndex){
        productList.nth(productIndex).click();
    }


}
