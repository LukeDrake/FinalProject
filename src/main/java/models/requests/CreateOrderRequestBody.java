package models.requests;
import lombok.Data;
import java.util.Arrays;
import java.util.List;

@Data
public class CreateOrderRequestBody {


    public static CreateOrderRequestBody NEW_ORDER = new CreateOrderRequestBody(Arrays.asList("61c0c5a71d1f82001bdaaa72","61c0c5a71d1f82001bdaaa6d"));

    public static final CreateOrderRequestBody NEW_ORDER_INCORRECT_HASH_INGREDIENT = new CreateOrderRequestBody( Arrays.asList("60d3b41abdacab0026a733c6", "609646e4dc916e00276b2870"));

    public static final CreateOrderRequestBody NEW_ORDER_NO_INGREDIENTS = new CreateOrderRequestBody( Arrays.asList());


      List<String> ingredients;


    public CreateOrderRequestBody(List<String> ingredients) {
         this.ingredients = ingredients;
    }
}
