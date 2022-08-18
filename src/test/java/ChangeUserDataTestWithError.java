import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import io.qameta.allure.junit4.DisplayName;
import models.errors.ErrorResponse;
import models.requests.ChangeUserRequestBody;
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

@Feature("API учебного сервиса Стеллар бургер")
@Story("Редактирование клиента без авторизации")
public class ChangeUserDataTestWithError extends BaseTest {
    @Before
    public void setup() {
        userSteps = new UserSteps();
        userSteps.createUser(CreateUserRequestBody.NEW_USER,
                SuccessResponse.class,
                HttpStatus.SC_OK);
        token = userSteps.loginUser(LoginUserRequestBody.USER_TO_LOGIN,
                LoginResponse.class,
                HttpStatus.SC_OK).getAccessToken();
    }

    @After
    public void teardown() {
        userSteps.deleteUser(HttpStatus.SC_ACCEPTED, token);
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Можно поменять клиенту имя")
    public void changeUserNameTestWithOutAuth() {
        ErrorResponse response = userSteps.changeUserWithOutAuth(ChangeUserRequestBody.CHANGE_NAME, HttpStatus.SC_UNAUTHORIZED);
        Assert.assertEquals("name was changed'", ErrorResponse.ERROR_NO_ACCESS, response);
    }


        @Test
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Можно поменять клиенту пароль")
    public void changeUserPasswordTestWithOutAuth() {
            ErrorResponse response = userSteps.changeUserWithOutAuth(ChangeUserRequestBody.CHANGE_PASSWORD, HttpStatus.SC_UNAUTHORIZED);
Assert.assertEquals("password was changed'", ErrorResponse.ERROR_NO_ACCESS, response);
}


    @Test
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Можно поменять клиенту почту")
    public void changeUserEmailTestWithOutAuth() {
        ErrorResponse response = userSteps.changeUserWithOutAuth(ChangeUserRequestBody.CHANGE_EMAIL, HttpStatus.SC_UNAUTHORIZED);
        Assert.assertEquals("email was changed'", ErrorResponse.ERROR_NO_ACCESS, response);
    }

}

