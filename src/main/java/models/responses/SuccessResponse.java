package models.responses;

import lombok.Data;

@Data
public class SuccessResponse {


    @Data
    public class User{
        String email;
        String name;
    }

    Boolean success;

    String accessToken;

    String refreshToken;

    User user;



}

