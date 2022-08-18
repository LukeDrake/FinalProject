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
import util.UserHelper;

import java.util.Arrays;
import java.util.Collection;

import static com.codeborne.selenide.Selenide.*;
import static util.UserHelper.*;
@Feature("API учебного сервиса СтелларБургер")
@Story("Регистрация клиента")
@RunWith(Parameterized.class)
public class RegistrationClientTest extends AbstractTest {
    @Parameterized.Parameters
        public static Collection<Object[]> data() {
            return Arrays.asList(new Object[][]{{"chrome"}, {"yandex"}});
        }

    String browser;

    public RegistrationClientTest(String browser) {
        this.browser = browser;
    }


    @Test
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Проверку флоу успешной регистрации")
    public void succesfullRegistration() {
        FakeUser fakeUser = UserHelper.getFakeUser();
        BrowserFactory.selectBrowser(browser);

        MainPage mainPage = open("/", MainPage.class);
        mainPage.navBar.clickNavbarButtonLK();
        LoginPage loginPage = Selenide.page(LoginPage.class);
        loginPage.clickRegistrationLink();

        RegistrationPage registrationPage = Selenide.page(RegistrationPage.class);
        registrationPage.registerUser(fakeUser.getName(),
                fakeUser.getEmail(),
                fakeUser.getPassword());

        registrationPage.checkSuccessfulRegistration();
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Проверку флоу неудачной регистрации")
    public void errorRegistration() {
        BrowserFactory.selectBrowser(browser);
        MainPage mainPage = open("/", MainPage.class);
        mainPage.navBar.clickNavbarButtonLK();
        LoginPage loginPage = Selenide.page(LoginPage.class);
        loginPage.clickRegistrationLink();
        RegistrationPage registrationPage = Selenide.page(RegistrationPage.class);
        registrationPage.registerUser(ERROR_USER.getName(),
                ERROR_USER.getEmail(),
                ERROR_USER.getPassword());
        registrationPage.checkFailRegistration();
    }

    @After
    public void teardown()
    {
        Selenide.closeWebDriver();
    }

}