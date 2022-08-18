import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import io.qameta.allure.junit4.DisplayName;
import models.errors.ErrorResponse;
import models.requests.CreateUserRequestBody;
import org.apache.http.HttpStatus;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import steps.UserSteps;

@Feature("API учебного сервиса Стеллар бургер")
@Story("Создание клиента - ошибки")

public class CreateUserTestError extends BaseTest{
    @Before
    public void setup() {
        userSteps = new UserSteps();
    }


    @Test
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Верная ошибка, если нехватает обязательных полей")
    public void createUserWithOutLogin() {
        ErrorResponse response = userSteps.createUser(CreateUserRequestBody.ERROR_USER_LOGIN,
                ErrorResponse.class,
                HttpStatus.SC_FORBIDDEN);
        Assert.assertEquals(ErrorResponse.ERROR_NOT_ENOUGH_FIELDS, response);
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Верная ошибка, если нехватает обязательных полей")
    public void createUserWithOutPassword() {
        ErrorResponse response = userSteps.createUser(CreateUserRequestBody.ERROR_USER_PASSWORD,
                ErrorResponse.class,
                HttpStatus.SC_FORBIDDEN);
        Assert.assertEquals(ErrorResponse.ERROR_NOT_ENOUGH_FIELDS, response);
    }


}
