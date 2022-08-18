package blocks;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class BurgerConstructor {
    @FindBy(how = How.CSS, using = "section[class*=\"BurgerIngredients_ingredients__\"] > div > div:nth-child(1)")
    SelenideElement bunTabBar;

    @FindBy(how = How.CSS, using = "section[class*=\"BurgerIngredients_ingredients__\"] > div > div:nth-child(2)")
    SelenideElement sauceTabBar;

    @FindBy(how = How.CSS, using = "section[class*=\"BurgerIngredients_ingredients__\"] > div > div:nth-child(3)")
    SelenideElement fillingTabBar;

    @FindBy(how = How.CSS, using = "div[class*=\"tab_tab_type_current\"]")
    SelenideElement currentSelectedTab;
    @Step("Выбор вкладки булки")
    public void clickBunTabBar(){
        bunTabBar.click();
    }

    @Step("Выбор вкладки соуса")
    public void clickSauceTabBar(){
        sauceTabBar.click();
    }

    @Step("Выбор вкладки начинки")
    public void clickFillingTabBar(){
        fillingTabBar.click();
    }

    public void checkCurrentTab(String text) {
        currentSelectedTab.shouldHave(Condition.text(text));
    }
}
