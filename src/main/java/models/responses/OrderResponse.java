package models.responses;

import lombok.Data;

import java.util.List;

@Data
public class OrderResponse {
    @Data
    class Order{
        Integer number;
    }

    SuccessOrderResponse.Order order;

    String name;

    Boolean success;


}
