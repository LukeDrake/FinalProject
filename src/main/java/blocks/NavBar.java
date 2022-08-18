package blocks;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class NavBar {
    @FindBy(how = How.XPATH, using = "./html/body/div/div/header/nav/a/p")
    private SelenideElement navbarButtonLK;

    @FindBy(how = How.CSS, using = "nav > div > a")
    private SelenideElement logoButton;

    @FindBy(how = How.CSS, using = "div > header > nav > ul > li:nth-child(1) > a > p")
    private SelenideElement ConstructorButton;

    @Step("Нажатие кнопки личный кабинет")
    public void clickNavbarButtonLK() {
        navbarButtonLK.click();
    }
    @Step("Выбор кнопки логотипа")
    public void clickLogoButton() {
        logoButton.click();
    }
    @Step("Выбор кнопки конструктора")
    public void clickConstructorButton() {
        ConstructorButton.click();
    }
}

