import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import io.qameta.allure.junit4.DisplayName;
import models.errors.ErrorResponse;
import models.requests.CreateUserRequestBody;
import models.requests.LoginUserRequestBody;
import models.responses.LoginResponse;
import models.responses.OrderListResponse;
import models.responses.SuccessResponse;
import org.apache.http.HttpStatus;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import steps.OrderSteps;
import steps.UserSteps;



@Feature("API учебного сервиса Яндекс.Самокат")
@Story("Проверка заказа")

public class OrderGetTest extends BaseTest {

    @Before
    public void setup() {
        userSteps = new UserSteps();
        userSteps.createUser(CreateUserRequestBody.NEW_USER,
                SuccessResponse.class,
                HttpStatus.SC_OK);
        orderSteps = new OrderSteps();
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
    @DisplayName("получение списка заказов")
    public void orderGetListWithoutAuth() {
        ErrorResponse response = orderSteps.takeOrders(HttpStatus.SC_UNAUTHORIZED);
        Assert.assertEquals(ErrorResponse.ERROR_NO_ACCESS, response);
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("получение списка заказов+auth")
    public void orderGetListWithAuth() {
        OrderListResponse orderListResponse = orderSteps.takeOrdersAuth(HttpStatus.SC_OK, token);
        Assert.assertEquals(true, orderListResponse.getSuccess());
    }

}
