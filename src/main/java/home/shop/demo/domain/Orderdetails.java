package home.shop.demo.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@NoArgsConstructor
@Data
@Entity
@Table(name="ORDERDETAILS")
@IdClass(OrderdetailsKey.class)
public class Orderdetails {

//    @ManyToOne(optional=false)
//    @JoinColumn(name="ORDERID")
//    private Orders order;
//
//    @ManyToOne
//    @JoinColumn(name="PRODUCTID")
//    private Product product;
//
//    @EmbeddedId
//    private OrderdetailsKey orderdetailsKey;

    @Id
    @Column(name = "ORDERID")
    private int orderId;

    @Id
    @Column(name="PRODUCTID")
    private String productId;

    @Basic
    @Column(name="UNITPRICE")
    private float unitPrice;

    @Basic
    @Column(name="DISCOUNT")
    private float discount;

    @Basic
    @Column(name="QUANTITY")
    private int quantity;

    //Constructor used by customer when creating the shopping cart
    public Orderdetails orderdetails(String productId, int quantity){
        Orderdetails newOrderdetails = new Orderdetails();
        newOrderdetails.setProductId(productId);
        newOrderdetails.setQuantity(quantity);
        return newOrderdetails;
    }

}

@Data
class OrderdetailsKey implements Serializable{
    private int orderId;

    private String productId;
}