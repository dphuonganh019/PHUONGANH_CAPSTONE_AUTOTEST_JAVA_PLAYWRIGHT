package tests;

import com.microsoft.playwright.*;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import report.ExtentReportExtension;
import utils.BrowserManager;
import utils.ConfigReader;
import utils.LogUtil;

@ExtendWith(ExtentReportExtension.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class BaseTest {
    static Playwright playwright;
    static Browser browser;
    static Page page;
    static BrowserContext context;
    static BrowserManager browserManager;

    @BeforeAll
    static void launchBrowser() {
        playwright = Playwright.create();
        browser = playwright.chromium().launch(
                new BrowserType.LaunchOptions().setHeadless(ConfigReader.getBoolean("headless")));
        context = browser.newContext();
        page = context.newPage();
        page.setDefaultTimeout(5000);
        browserManager = new BrowserManager(context, page);
        LogUtil.setCurrentPage(page);
    }

    @AfterAll
    static void closeBrowser() {
        LogUtil.clearPage();
        context.close();
        browser.close();
        playwright.close();
    }
}
