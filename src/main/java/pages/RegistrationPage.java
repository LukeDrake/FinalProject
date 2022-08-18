package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import util.PropertiesReader;

import static com.codeborne.selenide.WebDriverConditions.url;

public class RegistrationPage extends MainPage{
    //локатор для поля имя
    @FindBy(how = How.XPATH, using = "//*[.=\"Имя\"]/input")
    private SelenideElement nameInput;
    //локатор для поля фамилия
    @FindBy(how = How.XPATH, using = "//*[.=\"Email\"]/input")
    private SelenideElement emailInput;
    //локатор для поля адресс
    @FindBy(how = How.XPATH, using = "//*[.=\"Пароль\"]/input")
    private SelenideElement passwordInput;
    //локатор для кнопки далее
    @FindBy(how = How.XPATH, using = "//*[@id=\"root\"]/div/main/div/form/button")
    private SelenideElement finishButton;
    //локатор для даты заказа


    @FindBy(how = How.CSS, using = ".input__error.text_type_main-default")
    public SelenideElement errorPasswordText;

    @FindBy(how = How.CSS, using = "p > a")
    public SelenideElement enterLink;



    @Step("Регистрация нового пользователя")
    public void registerUser(String name, String email, String password) {
        nameInput.setValue(name);
        emailInput.setValue(email);
        passwordInput.setValue(password);
        finishButton.click();
    }

    @Step("Выбор кнопки входа")
    public void clickEnterLink(){
        enterLink.click();
    }

    @Step("Проверка переадресации на страницу логина")
    public void checkSuccessfulRegistration(){
        Selenide.webdriver().shouldHave(url(PropertiesReader.getProperty("url")+"/login"));
    }

    @Step("Проверка наличие текста ошибки")
    public void checkFailRegistration(){
        errorPasswordText.shouldBe(Condition.visible);
    }
}

