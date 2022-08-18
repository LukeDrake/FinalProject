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
@Story("Логин клиента")
public class LoginUserTest extends BaseTest {
    @Before
    public void setup() {
       userSteps = new UserSteps();
        userSteps.createUser(CreateUserRequestBody.NEW_USER,
                SuccessResponse.class,
                HttpStatus.SC_OK);
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
    @DisplayName("Можно войти под созданным клиентом")
    public void loginUserTest() {
       LoginResponse loginResponse =  userSteps.loginUser(LoginUserRequestBody.USER_TO_LOGIN,
                LoginResponse.class,
                HttpStatus.SC_OK);
        Assert.assertEquals(true, loginResponse.getSuccess());

    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Нельзя войти под несуществующим клиентом")
    public void loginUserWitNoNameOnTest() {
        ErrorResponse response = userSteps.loginUser(LoginUserRequestBody.COURIER_TO_LOGIN_ALT,
                ErrorResponse.class,
                HttpStatus.SC_UNAUTHORIZED);
        Assert.assertEquals(ErrorResponse.ERROR_NOEXIST_COURIER, response);

    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Верная ошибка, если нехватает обязательных полей")
    public void loginUserWithNotAllFields() {
        ErrorResponse response = userSteps.loginUser(LoginUserRequestBody.COURIER_TO_LOGIN_ERROR,
                ErrorResponse.class,
                HttpStatus.SC_UNAUTHORIZED);
        Assert.assertEquals(ErrorResponse.ERROR_NOT_ENOUGH_FIELD, response);


    }
}
