package models.requests;

import lombok.Data;
import config.TestData;

@Data
public class LoginUserRequestBody {
    public static final LoginUserRequestBody USER_TO_LOGIN = new LoginUserRequestBody(TestData.EMAIL,TestData.PASSWORD);

    public static final LoginUserRequestBody USER_TO_LOGIN_CHANGED_PASSWORD = new LoginUserRequestBody(TestData.EMAIL,TestData.CHANGED_PASSWORD);

    public static final LoginUserRequestBody COURIER_TO_LOGIN_ALT = new LoginUserRequestBody(TestData.EMAIL_NOT_EXIST,TestData.PASSWORD);
    public static final LoginUserRequestBody COURIER_TO_LOGIN_ERROR = new LoginUserRequestBody(TestData.EMAIL,"");


    String email;
    String password;

    public LoginUserRequestBody(String email, String password) {
        this.email = email;
        this.password = password;
    }

}
