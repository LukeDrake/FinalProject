import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import io.qameta.allure.junit4.DisplayName;
import models.errors.ErrorResponse;
import models.requests.CreateOrderRequestBody;
import models.requests.CreateUserRequestBody;
import models.requests.LoginUserRequestBody;
import models.responses.LoginResponse;
import models.responses.OrderResponse;
import models.responses.SuccessResponse;
import org.apache.http.HttpStatus;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import steps.OrderSteps;
import steps.UserSteps;


@Feature("API учебного сервиса Яндекс.Самокат")
@Story("Создание заказа")
public class OrderCreateTest extends BaseTest {
    @Before
    public void setup() {
        orderSteps = new OrderSteps();
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
    @DisplayName("Заказ можно создать с разными ингридиентами - без авторизации")
    public void createOrderTest() {
        OrderResponse orderResponse =  orderSteps.createOrderWithoutAuth(CreateOrderRequestBody.NEW_ORDER,
                OrderResponse.class,
                HttpStatus.SC_OK);
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Заказ нельзя создать без ингридиентов - без авторизации")
    public void createOrderTestWithoutIngredients() {
        ErrorResponse errorResponseWithoutIngredients = orderSteps.createOrderWithoutAuth(CreateOrderRequestBody.NEW_ORDER_NO_INGREDIENTS,
                ErrorResponse.class,
                HttpStatus.SC_BAD_REQUEST);
        Assert.assertEquals("some ingredients was included",ErrorResponse.ERROR_NO_INGREDIENT_IDS,errorResponseWithoutIngredients);
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Заказ нельзя создать без ингридиентов - без авторизации")
    public void createOrderTestWithIncorrectIngredients() {
        ErrorResponse errorResponseWithIncorrectIngredients = orderSteps.createOrderWithoutAuth(CreateOrderRequestBody.NEW_ORDER_INCORRECT_HASH_INGREDIENT,
                ErrorResponse.class,
                HttpStatus.SC_INTERNAL_SERVER_ERROR);
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Заказ можно создать с разными ингридиентами - с авторизацией")
    public void createOrderTestAuth() {
        OrderResponse orderResponse = orderSteps.createOrderWithAuth(CreateOrderRequestBody.NEW_ORDER,
                OrderResponse.class,
                HttpStatus.SC_OK,
                token);
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Заказ нельзя создать без ингридиентов - с авторизацией")
    public void createOrderTestAuthWithoutIngredients() {
        ErrorResponse errorResponseWithoutIngredients = orderSteps.createOrderWithAuth(CreateOrderRequestBody.NEW_ORDER_NO_INGREDIENTS,
                ErrorResponse.class,
                HttpStatus.SC_BAD_REQUEST,
                token);
        Assert.assertEquals("some ingredients was included",ErrorResponse.ERROR_NO_INGREDIENT_IDS,errorResponseWithoutIngredients);
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Заказ нельзя создать без ингридиентов - с авторизацией")
    public void createOrderTestAuthWithIncorrectIngredients() {
        ErrorResponse errorResponseWithIncorrectIngredients = orderSteps.createOrderWithAuth(CreateOrderRequestBody.NEW_ORDER_INCORRECT_HASH_INGREDIENT,
                ErrorResponse.class,
                HttpStatus.SC_INTERNAL_SERVER_ERROR,
                token);
    }
}