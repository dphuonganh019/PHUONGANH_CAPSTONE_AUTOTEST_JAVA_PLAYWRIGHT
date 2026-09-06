package utils;

import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.Page;

public class BrowserManager {
    private final BrowserContext context;
    private Page currentTab;

    public BrowserManager(BrowserContext context, Page initialTab) {
        this.context = context;
        this.currentTab = initialTab;
    }

    public String getCurrentTabUrl() {
        LogUtil.info("The current URL is: " + currentTab.url());
        return currentTab.url();
    }
}
