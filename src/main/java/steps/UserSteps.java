package steps;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import models.errors.ErrorResponse;
import models.requests.ChangeUserRequestBody;
import models.requests.CreateUserRequestBody;
import models.requests.LoginUserRequestBody;
import models.responses.LoginResponse;
import models.responses.SuccessResponse;
import util.RestAssuredClient;


import static config.ApiEndpoints.*;
import static config.ConnectionSettings.BASE_URL;
import static io.restassured.RestAssured.given;

public class UserSteps extends RestAssuredClient {
    public UserSteps() {
        super(BASE_URL);
    }

    @Step("Создаём клиента")
    public <T> T createUser(CreateUserRequestBody createUserRequestBody, Class<T> clazz, Integer code) {
        Response response = given()
                .body(createUserRequestBody)
                .post(USER_CREATE_ENDPOINT);

        response.then().statusCode(code);

        if (SuccessResponse.class.equals(clazz)) {
            return clazz.cast(response.as(SuccessResponse.class));
        } else if (ErrorResponse.class.equals(clazz)) {
            return clazz.cast(response.as(ErrorResponse.class));
        }

        return null;
    }

    @Step("Логинимся под определённым клиентом")
    public <T> T loginUser(LoginUserRequestBody loginUserRequestBody, Class<T> clazz, Integer code) {
        Response response = given()
                .body(loginUserRequestBody)
                .post(LOGIN_ENDPOINT);

        response.then().statusCode(code);

        if (LoginResponse.class.equals(clazz)) {
            return clazz.cast(response.as(LoginResponse.class));
        } else if (ErrorResponse.class.equals(clazz)) {
            return clazz.cast(response.as(ErrorResponse.class));
        }

        return null;

    }

    @Step("Удаляем клиента")
    public SuccessResponse deleteUser(Integer code, String token) {
        Response response = given()
                .headers("Authorization", token)
                .delete(USER_WORK_ENDPOINT);
        response.then().statusCode(code);
        return response.as(SuccessResponse.class);
    }


    @Step("Редактируем клиента")
    public ErrorResponse changeUserWithOutAuth(ChangeUserRequestBody changeUserRequestBody, Integer code) {
        Response response = given()
                .body(changeUserRequestBody)
                .patch(USER_WORK_ENDPOINT);
        response.then().statusCode(code);
        return response.as(ErrorResponse.class);
    }

    @Step("Редактируем клиента")
    public SuccessResponse changeUserWithAuth(ChangeUserRequestBody changeUserRequestBody, Integer code, String token) {
        Response response = given()
                .headers("Authorization", token)
                .body(changeUserRequestBody)
                .patch(USER_WORK_ENDPOINT);
        response.then().statusCode(code);
        return response.as(SuccessResponse.class);
    }

    @Step("Получаем клиента")
    public SuccessResponse getUser(Integer code, String token) {
        Response response = given()
                .headers("Authorization", token)
                .get(USER_WORK_ENDPOINT);
        response.then().statusCode(code);
        return response.as(SuccessResponse.class);
    }
}



