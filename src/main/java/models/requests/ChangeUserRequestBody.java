package models.requests;

import lombok.Data;
import config.TestData;



@Data
public class ChangeUserRequestBody {

    public static final ChangeUserRequestBody CHANGE_NAME = new ChangeUserRequestBody(null,null, TestData.CHANGED_NAME);
    public static final ChangeUserRequestBody CHANGE_PASSWORD = new ChangeUserRequestBody(null, TestData.CHANGED_PASSWORD, null);
    public static final ChangeUserRequestBody CHANGE_EMAIL = new ChangeUserRequestBody(TestData.CHANGED_EMAIL,null, null);


    String email;
    String password;
    String name;

    public ChangeUserRequestBody(String email, String password, String name) {
        this.email = email;
        this.password = password;
        this.name = name;
    }

}
