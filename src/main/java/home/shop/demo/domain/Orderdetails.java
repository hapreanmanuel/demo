package home.shop.demo.domain;

import lombok.Data;

import javax.persistence.*;


@Data
@Entity
@Table(name="ORDERDETAILS")
public class Orderdetails {

    @Id
    @Column(name = "ORDERID", nullable = false)
    private int orderId;

    @Basic
    @Column(name="PRODUCTID")
    private String productId;

    @Basic
    @Column(name="UNITPRICE")
    private float unitPrice;

    @Basic
    @Column(name="DISCOUNT")
    private float discount;

}
