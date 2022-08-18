package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import util.PropertiesReader;

import static com.codeborne.selenide.WebDriverConditions.url;

public class AccountPage extends MainPage{
    @FindBy(how = How.XPATH, using = "//*[@id=\"root\"]/div/main/div/nav/ul/li[3]/button")
    private SelenideElement exitButton;

    @Step("Проверка успешности входа в аккуант")
    public void checkSuccessfulEnterToAccount(){
        Selenide.webdriver().shouldHave(url(PropertiesReader.getProperty("url")+"/account/profile"));
        exitButton.shouldBe(Condition.visible);
    }
    @Step("Выбор кнопки выхода из профиля")
    public void clickExitButton(){
        exitButton.click();
    }


}
