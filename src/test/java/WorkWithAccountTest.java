import com.codeborne.selenide.Selenide;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import pages.AccountPage;
import pages.LoginPage;
import pages.MainPage;
import util.BrowserFactory;

import java.util.Arrays;
import java.util.Collection;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.sessionId;
import static util.UserHelper.REGISTERED_USER;
@RunWith(Parameterized.class)
@Feature("API учебного сервиса СтелларБургер")
@Story("Работа с аккаунтом")
public class WorkWithAccountTest extends AbstractTest {
    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{{"chrome"}, {"yandex"}});
    }

    String browser;

    public WorkWithAccountTest(String browser) {
        this.browser = browser;
    }


    MainPage mainPage;

    @Before
    public void setUp(){
        BrowserFactory.selectBrowser(browser);
        mainPage =
                open("/", MainPage.class);
        mainPage.navBar.clickNavbarButtonLK();
        LoginPage loginPage = Selenide.page(LoginPage.class);
        loginPage.enterByAccountButton(REGISTERED_USER.getEmail(),REGISTERED_USER.getPassword());
        mainPage.navBar.clickNavbarButtonLK();

    }


    @Test
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Проверку перехода по клику на кнопку Личный Кабинет ")
    public void ChangeActivePageFromLk() {
        BrowserFactory.selectBrowser(browser);
        AccountPage accountPage = Selenide.page((AccountPage.class));
        accountPage.checkSuccessfulEnterToAccount();
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Проверка выхода из Личного Кабинета ")
    public void ExitFromLk() {
        BrowserFactory.selectBrowser(browser);
        AccountPage accountPage = Selenide.page((AccountPage.class));
        accountPage.clickExitButton();
        LoginPage loginPage = Selenide.page(LoginPage.class);
        loginPage.checkSuccessfulEnterToLoginPage();
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Проверку перехода по клику на кнопку Конструктор ")
    public void CheckPressingConstructorFromLk() {
        BrowserFactory.selectBrowser(browser);
        mainPage.navBar.clickLogoButton();
        mainPage.checkSuccessfulRedirection();
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Проверку перехода по клику на логотип")
    public void CheckPressingLogoFromLk() {
        BrowserFactory.selectBrowser(browser);
        mainPage.navBar.clickConstructorButton();
        mainPage.checkSuccessfulRedirection();

    }
    @After
    public void teardown()
    {
        Selenide.closeWebDriver();
    }


}

