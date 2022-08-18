import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import io.qameta.allure.junit4.DisplayName;
import models.errors.ErrorResponse;
import models.requests.CreateUserRequestBody;
import models.requests.LoginUserRequestBody;
import models.responses.LoginResponse;
import models.responses.SuccessResponse;
import org.apache.http.HttpStatus;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import steps.UserSteps;

@Feature("API учебного сервиса Яндекс.Самокат")
@Story("Создание клиента")
public class UserCreateTest extends BaseTest{
    @Before
    public void setup(){
        userSteps = new UserSteps();

    }

    @After
    public void teardown() {
        String token = userSteps.loginUser(LoginUserRequestBody.USER_TO_LOGIN,
                LoginResponse.class,
                HttpStatus.SC_OK).getAccessToken();
        userSteps.deleteUser(HttpStatus.SC_ACCEPTED, token);
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Клиента можно создать")
    public void createUserTest() {
        userSteps.createUser(CreateUserRequestBody.NEW_USER,
                SuccessResponse.class,
                HttpStatus.SC_OK);
            }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Нельзя создать двух одинаковых Клиентов")
    public void createUserWithSameNameTest() {
        userSteps.createUser(CreateUserRequestBody.NEW_USER,
                SuccessResponse.class,
                HttpStatus.SC_OK);
        ErrorResponse errorExistCourier = userSteps.createUser(CreateUserRequestBody.NEW_USER,
                ErrorResponse.class,
                HttpStatus.SC_FORBIDDEN);
        Assert.assertEquals(errorExistCourier, ErrorResponse.ERROR_EXIST_COURIER);

    }


}
