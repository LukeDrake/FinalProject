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
public class ChangeUserDataTest extends BaseTest{

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
    public void changeUserNameTest() {
        String responseName = userSteps.changeUserWithAuth(ChangeUserRequestBody.CHANGE_NAME, HttpStatus.SC_OK, token).getUser().getName();
        Assert.assertEquals("name didn't changed'", TestData.CHANGED_NAME, responseName);
    }


    @Test
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Можно поменять клиенту почту ")
    public void changeUserEmailTest() {
        String responseEmail = userSteps.changeUserWithAuth(ChangeUserRequestBody.CHANGE_EMAIL, HttpStatus.SC_OK, token).getUser().getEmail();
        Assert.assertEquals("email didn't changed'", TestData.CHANGED_EMAIL, responseEmail);
    }

}
