package models.responses;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;


@Data
public class OrderListResponse {
    @Data
    class Orders{
        List<String> ingredients;
        Integer id;
        String status;
        Integer number;
        String createdAt;
        String updatedAt;
    }

    ArrayList<OrderListResponse.Orders> orders;

    Integer total;
    Integer totalToday;

    Boolean success;



}
