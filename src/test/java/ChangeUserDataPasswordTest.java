import config.TestData;
        import io.qameta.allure.Feature;
        import io.qameta.allure.Severity;
        import io.qameta.allure.SeverityLevel;
        import io.qameta.allure.Story;
        import io.qameta.allure.junit4.DisplayName;
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
@Story("Редактирование клиента")
public class ChangeUserDataPasswordTest extends BaseTest {
    String changedToken = null;

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
        userSteps.deleteUser(HttpStatus.SC_ACCEPTED, changedToken);
    }


    @Test
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Можно поменять клиенту пароль")
    public void changeUserPasswordTest() {
        userSteps.changeUserWithAuth(ChangeUserRequestBody.CHANGE_PASSWORD, HttpStatus.SC_OK, token);
        LoginResponse loginResponse = userSteps.loginUser(LoginUserRequestBody.USER_TO_LOGIN_CHANGED_PASSWORD,
                LoginResponse.class,
                HttpStatus.SC_OK);
        changedToken = loginResponse.getAccessToken();
        Assert.assertEquals("password didn't changed'", true , loginResponse.getSuccess());
    }
}