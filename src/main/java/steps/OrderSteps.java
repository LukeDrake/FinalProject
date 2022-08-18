package steps;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import models.errors.ErrorResponse;
import models.errors.ErrorResponse;
import models.requests.CreateOrderRequestBody;
import models.responses.LoginResponse;
import models.responses.OrderListResponse;
import models.responses.OrderResponse;
import models.responses.SuccessOrderResponse;
import util.RestAssuredClient;

import static config.ApiEndpoints.ORDER_ENDPOINT;
import static config.ConnectionSettings.BASE_URL;
import static io.restassured.RestAssured.given;

public class OrderSteps extends RestAssuredClient {
    public OrderSteps() {
        super(BASE_URL);
    }

    @Step("Создание заказа")
    public <T> T createOrderWithoutAuth(CreateOrderRequestBody createOrderRequestBody, Class<T> clazz, Integer code) {
        Response response = given()
                .body(createOrderRequestBody)
                .post(ORDER_ENDPOINT);
        response.then().statusCode(code);

        if (OrderResponse.class.equals(clazz)) {
            return clazz.cast(response.as(OrderResponse.class));
        } else if (ErrorResponse.class.equals(clazz)) {
            return clazz.cast(response.as(ErrorResponse.class));
        }

        return null;
    }

    @Step("Создание заказа+auth")
    public <T> T createOrderWithAuth(CreateOrderRequestBody createOrderRequestBody, Class<T> clazz, Integer code, String token) {
        Response response = given()
                .headers("Authorization", token)
                .body(createOrderRequestBody)
                .post(ORDER_ENDPOINT);
        response.then().statusCode(code);

        if (OrderResponse.class.equals(clazz)) {
            return clazz.cast(response.as(OrderResponse.class));
        } else if (ErrorResponse.class.equals(clazz)) {
            return clazz.cast(response.as(ErrorResponse.class));
        }

        return null;
    }

    @Step("Получение списка заказов")
    public ErrorResponse takeOrders(Integer code) {
        Response response = given()
               // .body(orderListResponse)
                .get(ORDER_ENDPOINT);
        response.then().statusCode(code);
        return response.as(ErrorResponse.class);
    }

    @Step("Получение списка заказов")
    public OrderListResponse takeOrdersAuth(Integer code, String token) {
        Response response = given()
                .headers("Authorization", token)
                // .body(orderListResponse)
                .get(ORDER_ENDPOINT);
        response.then().statusCode(code);
        return response.as(OrderListResponse.class);
    }



}
