package pages;

import blocks.BurgerConstructor;
import blocks.NavBar;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import util.PropertiesReader;

import static com.codeborne.selenide.WebDriverConditions.url;

public class MainPage {

    public NavBar navBar;
    public BurgerConstructor burgerConstructor;

    @FindBy(how = How.CSS, using = "button[class*=\"button_button__\"]")
    private SelenideElement buttonAccountEnter;

    @Step("Выбор кнопки входа в личный кабинет на основном экране")
    public void clickEnterAccount() {
        buttonAccountEnter.click();
    }

    public MainPage() {
        this.navBar = Selenide.page(NavBar.class);
        this.burgerConstructor = Selenide.page(BurgerConstructor.class);
    }

    @Step("Проверка текста на кнопке")
    public void checkButtonText(String text) {
        buttonAccountEnter.shouldHave(Condition.text(text));
    }

    @Step("Проверка успешности редиректа")
    public void checkSuccessfulRedirection(){
        Selenide.webdriver().shouldHave(url(PropertiesReader.getProperty("url")+"/"));
    }
}