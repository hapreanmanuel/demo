package home.shop.demo.domain;

import lombok.Data;

@Data
public class OrderdetailsRequestBody {

    private String productId;

    private int quantity;

}
