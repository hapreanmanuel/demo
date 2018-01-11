package home.shop.demo.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Id;
import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Data
//@Embeddable
public class OrderdetailsKey implements Serializable {

    //@Id
    //@Column(name="ORDERID")
    private int orderId;

    //@Id
    //@Column(name="PRODUCTID")
    private String productId;

}
