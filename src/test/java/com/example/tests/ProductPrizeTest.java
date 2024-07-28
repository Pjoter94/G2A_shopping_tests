package tests;
import pages.HomePage;
import com.microsoft.playwright.*;
import org.junit.jupiter.api.*;

public class ProductPrizeTest {
    private Browser browser;
    private Page page;

    @BeforeEach
    public void setup() {
        Playwright playwright = Playwright.create();
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        page = browser.newPage();
    }

    @AfterEach
    public void teardown() {
        page.close();
        browser.close();
    }

    @Test

    public void testProductPrice() {

        HomePage homePage = new HomePage(page);

        // Go to the G2A homepage
        homePage.navigate();
        homePage.searchProduct("Cyberpunk 2077");

    }
}
