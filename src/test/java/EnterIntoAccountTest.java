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
import pages.LoginPage;
import pages.MainPage;
import pages.RegistrationPage;
import util.BrowserFactory;

import java.util.Arrays;
import java.util.Collection;

import static com.codeborne.selenide.Selenide.open;
import static util.UserHelper.REGISTERED_USER;

@Feature("API учебного сервиса СтелларБургер")
@Story("Вход в аккаунт")

@RunWith(Parameterized.class)
public class EnterIntoAccountTest extends AbstractTest {
    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{{"chrome"}, {"yandex"}});
    }

    String browser;
    MainPage mainPage;

    public EnterIntoAccountTest(String browser) {
        this.browser = browser;
    }
    @Test
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Вход по кнопке Личный кабинет")
    public void enterByAccountButton() {
        BrowserFactory.selectBrowser(browser);
        mainPage =
                open("/", MainPage.class);
        mainPage.clickEnterAccount();
        LoginPage loginPage = Selenide.page(LoginPage.class);
        loginPage.enterByAccountButton(REGISTERED_USER.getEmail(), REGISTERED_USER.getPassword());

        loginPage.checkSuccessfulLogin();
        mainPage.checkButtonText("Оформить заказ");

    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Вход по кнопке войти в Аккаунт")
    public void enterByLoginButton() {
        BrowserFactory.selectBrowser(browser);
        mainPage =
                open("/", MainPage.class);
        mainPage.navBar.clickNavbarButtonLK();
        LoginPage loginPage = Selenide.page(LoginPage.class);
        loginPage.enterByAccountButton(REGISTERED_USER.getEmail(), REGISTERED_USER.getPassword());

        loginPage.checkSuccessfulLogin();
        mainPage.checkButtonText("Оформить заказ");

    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Вход по кнопке войти в с экрана регистрации")
    public void enterByLoginButtonFromRegistrationWindow() {
        BrowserFactory.selectBrowser(browser);
        mainPage =
                open("/", MainPage.class);
        mainPage.clickEnterAccount();
        LoginPage loginPage = Selenide.page(LoginPage.class);
        loginPage.clickRegistrationLink();
        RegistrationPage registrationPage = Selenide.page(RegistrationPage.class);
        registrationPage.clickEnterLink();
        loginPage.enterByAccountButton(REGISTERED_USER.getEmail(), REGISTERED_USER.getPassword());

        loginPage.checkSuccessfulLogin();
        mainPage.checkButtonText("Оформить заказ");
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Вход по кнопке войти с экрана восстановления пароля")
    public void enterByLoginButtonFromResetPasswordWindow() {
        BrowserFactory.selectBrowser(browser);
        mainPage =
                open("/", MainPage.class);
        mainPage.clickEnterAccount();
        LoginPage loginPage = Selenide.page(LoginPage.class);
        loginPage.clickResetPasswordButton();
        RegistrationPage registrationPage = Selenide.page(RegistrationPage.class);
        registrationPage.clickEnterLink();
        loginPage.enterByAccountButton(REGISTERED_USER.getEmail(), REGISTERED_USER.getPassword());

        loginPage.checkSuccessfulLogin();
        mainPage.checkButtonText("Оформить заказ");

    }
    @After
    public void teardown()
    {
        Selenide.closeWebDriver();
    }

}
