package models.responses;

import lombok.Data;
@Data
public class SuccessOrderResponse {

    @Data
    class Order{
        Integer number;
    }

    SuccessOrderResponse.Order order;

    String name;

    Boolean success;


}


