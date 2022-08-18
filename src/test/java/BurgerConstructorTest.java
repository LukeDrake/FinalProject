import com.codeborne.selenide.Selenide;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import pages.MainPage;
import util.BrowserFactory;

import java.util.Arrays;
import java.util.Collection;

import static com.codeborne.selenide.Selenide.open;

@Feature("API учебного сервиса СтелларБургер")
@Story("Бургер конструктор")

@RunWith(Parameterized.class)
public class BurgerConstructorTest extends AbstractTest{
    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{{"chrome"}, {"yandex"}});
    }

    String browser;
    MainPage mainPage;

    public BurgerConstructorTest(String browser) {
        this.browser = browser;
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Выбрать раздел булку")
    public void checkConstructorTabBarBun() {
        BrowserFactory.selectBrowser(browser);
        mainPage =
                open("/", MainPage.class);
        mainPage.burgerConstructor.clickSauceTabBar();
        mainPage.burgerConstructor.clickBunTabBar();
        mainPage.burgerConstructor.checkCurrentTab("Булки");
    }
        @Test
        @Severity(SeverityLevel.NORMAL)
        @DisplayName("Выбрать раздел cоус")
        public void checkConstructorTabBarSause() {
            BrowserFactory.selectBrowser(browser);
            mainPage =
                    open("/", MainPage.class);
            mainPage.burgerConstructor.clickSauceTabBar();
            mainPage.burgerConstructor.checkCurrentTab("Соусы");
        }


        @Test
        @Severity(SeverityLevel.NORMAL)
        @DisplayName("Выбрать раздел начинки")
    public void checkConstructorTabBarFilling() {
            BrowserFactory.selectBrowser(browser);
        mainPage =
                open("/", MainPage.class);
        mainPage.burgerConstructor.clickFillingTabBar();
        mainPage.burgerConstructor.checkCurrentTab("Начинки");

    }
    @After
    public void teardown()
    {
        Selenide.closeWebDriver();
    }

}
