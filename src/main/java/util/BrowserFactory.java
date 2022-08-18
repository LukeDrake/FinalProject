package util;

import com.codeborne.selenide.Configuration;

public class BrowserFactory {
    public static void selectBrowser(String browser) {
        switch (browser) {
            case "chrome":
                Configuration.browser = "chrome";
                Configuration.headless = true;
                System.setProperty("webdriver.chrome.driver", "");
                break;
            case "yandex":
                Configuration.browser = "chrome";
                Configuration.headless = true;
                System.setProperty("webdriver.chrome.driver", "drivers/yandexdriver.exe");
                break;
            default:
                throw new IllegalStateException("Browser " + browser + " not supported in tests");
        }
    }
}
