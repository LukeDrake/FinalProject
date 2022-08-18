package pages;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import util.PropertiesReader;

import static com.codeborne.selenide.WebDriverConditions.url;

public class LoginPage extends MainPage{
    @FindBy(how = How.XPATH, using = "//*[.=\"Email\"]/input")
    private SelenideElement emailEnter;

    @FindBy(how = How.XPATH, using = "//*[.=\"Пароль\"]/input")
    private SelenideElement passwordEnter;

    @FindBy(how = How.CSS, using = ".button_button_size_medium__3zxIa")
    private SelenideElement buttonEnter;

    @FindBy(how = How.XPATH, using = "//*[@id=\"root\"]/div/main/div/div/p[2]/a")
    private SelenideElement resetPasswordButton;

    @FindBy(how = How.CSS, using = "p.text:nth-child(1) > a")
    private SelenideElement buttonEnterRegistration;

    @Step("Выбор кнопки регистрации")

    public void clickRegistrationLink(){
        buttonEnterRegistration.click();
    }

    @Step("Выбор кнопки сброса пароля")
    public void clickResetPasswordButton() {
        resetPasswordButton.click();
    }

    @Step("Вход в личный кабинет")
    public void enterByAccountButton(String email, String password) {
        emailEnter.setValue(email);
        passwordEnter.setValue(password);
        buttonEnter.click();
    }

    @Step("Проверка успешности логина")
    public void checkSuccessfulLogin(){
        Selenide.webdriver().shouldHave(url(PropertiesReader.getProperty("url")+"/"));
    }

    @Step("Проверка успешного перехода на страницу логина")
    public void checkSuccessfulEnterToLoginPage(){
        Selenide.webdriver().shouldHave(url(PropertiesReader.getProperty("url")+"/login"));

    }
}
