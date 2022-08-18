package models.errors;

import lombok.Data;

@Data
public class ErrorResponse {
    public static final ErrorResponse ERROR_EXIST_COURIER = new ErrorResponse(false, "User already exists");
    public static final ErrorResponse ERROR_NOT_ENOUGH_FIELDS = new ErrorResponse(false,"Email, password and name are required fields");
    public static final ErrorResponse ERROR_NO_ACCESS = new ErrorResponse(false,"You should be authorised");
    public static final ErrorResponse ERROR_NOEXIST_COURIER = new ErrorResponse(false,"email or password are incorrect");
    public static final ErrorResponse ERROR_NOT_ENOUGH_FIELD = new ErrorResponse(false,"email or password are incorrect");
    public static final ErrorResponse ERROR_NO_INGREDIENT_IDS = new ErrorResponse(false,"Ingredient ids must be provided");


    Boolean success;
    String message;

    public ErrorResponse(Boolean success, String message) {
        this.success = success;
        this.message = message;
    }
}
