package com.example.tests;

import com.example.pages.CartPage;
import com.example.pages.HomePage;
import com.example.pages.ProductPage;
import com.example.pages.SearchPage;
import com.microsoft.playwright.*;
import io.qameta.allure.*;
import org.junit.jupiter.api.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class ProductPrizeTest {
    private Playwright playwright;
    private Browser browser;
    private Page page;

    @BeforeEach
    public void setup() {
        playwright = Playwright.create();
        BrowserType.LaunchOptions options = new BrowserType.LaunchOptions()
                .setHeadless(false)
                .setArgs(Arrays.asList(new String[]{"--disable-web-security", "--disable-blink-features=AutomationControlled"}));
        browser = playwright.chromium().launch(options);
        Browser.NewContextOptions contextOptions = new Browser.NewContextOptions();
        Map<String, String> headers = new HashMap<>();
        headers.put("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36");
        headers.put("Accept-Language","en-US");
        contextOptions.setExtraHTTPHeaders(headers);
        BrowserContext context = browser.newContext(contextOptions);
        page = context.newPage();

    }

    @AfterEach
    public void teardown() {
        if (page != null) page.close();
        if (browser != null) browser.close();
        if (playwright != null) playwright.close();
    }

    @Test
    @DisplayName("Verify Product Price in Cart Matches Product Page Price")
    @Description("This test verifies that the price of a product on the product page matches the price in the cart.")
    public void testProductPrice() {

        String productName = System.getProperty("product", "Witcher 3");

        HomePage homePage = new HomePage(page);
        ProductPage productPage = new ProductPage(page);
        CartPage cartPage = new CartPage(page);
        SearchPage searchPage = new SearchPage(page);


        Allure.step("Go to the G2A homepage");
        homePage.navigate();
        homePage.acceptCookie();
        Assertions.assertEquals(homePage.homeUrl, page.url());
        homePage.takeScreenshot("home_page");

        Allure.step("Search the product "+productName);
        homePage.searchProduct(productName);


        Allure.step("Select first product in the list");
        searchPage.takeScreenshot("list_of_products");
        searchPage.selectProduct(0);


        Allure.step("Get the price of the product");
        String productPrice = productPage.getProductPrice();
        productPage.takeScreenshot("product_page");

        Allure.step("Add the product to the cart and verify prices");
        productPage.addToCart();
        if (!Objects.equals(page.url(), cartPage.cartUrl)){
            cartPage.navigateToCart();
        }
        Assertions.assertEquals(cartPage.cartUrl, page.url());
        String cartPrice = cartPage.getCartPrice();
        cartPage.takeScreenshot("cart_page");
        Assertions.assertEquals(productPrice, cartPrice, "Price mismatch: " + productPrice + " != " + cartPrice);
    }
}
