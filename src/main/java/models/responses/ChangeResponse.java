package models.responses;

        import lombok.Data;

@Data
public class ChangeResponse {

    @Data
    class User{
        String email;
        String name;
    }

    SuccessResponse.User user;

    Boolean success;

    String accessToken;

    String refreshToken;

}