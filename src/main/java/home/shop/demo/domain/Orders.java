package home.shop.demo.domain;

import lombok.Data;

import javax.persistence.*;


@Data
@Entity
@Table(name="ORDERS")
public class Orders {

    @Id
    @Column(name = "ORDERID", nullable = false)
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int orderId;

    @Basic
    @Column(name="CUSTOMERID")
    private String customerId;

    @Basic
    @Column(name="CUSTOMERNAME")
    private String customerName;

    @Basic
    @Column(name="EMPLOYEEID")
    private int employeeId;

    @Basic
    @Column(name="ORDERDATE")
    private String orderDate;

    @Basic
    @Column(name="REQUIREDDATE")
    private String requiredDate;

    @Basic
    @Column(name="SHIPPEDDATE")
    private String shippedDate;

    @Basic
    @Column(name="SHIPVIA")
    private int shipVia;

    @Basic
    @Column(name="FREIGHT")
    private int freight;

    @Basic
    @Column(name="SHIPNAME")
    private String shipName;

    @Basic
    @Column(name="SHIPADDRESS")
    private String shipAddress;

    @Basic
    @Column(name="SHIPCITY")
    private String shipCity;

    @Basic
    @Column(name="SHIPREGION")
    private String shipRegion;

    @Basic
    @Column(name="SHIPPOSTALCODE")
    private String shipPostalCode;

    @Basic
    @Column(name="SHIPCOUNTRY")
    private String shipCountry;

}
