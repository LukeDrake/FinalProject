package models.requests;

import config.TestData;
import lombok.Data;

@Data
public class CreateUserRequestBody {
    public static final CreateUserRequestBody NEW_USER = new CreateUserRequestBody(TestData.EMAIL,TestData.PASSWORD,TestData.NAME);
    public static final CreateUserRequestBody ERROR_USER_LOGIN = new CreateUserRequestBody(null,TestData.PASSWORD,TestData.NAME);
    public static final CreateUserRequestBody ERROR_USER_PASSWORD = new CreateUserRequestBody(TestData.EMAIL,null,TestData.NAME);

    String email;
    String password;
    String name;

    public CreateUserRequestBody(String email, String password, String name) {
        this.email = email;
        this.password = password;
        this.name = name;

    }
}

