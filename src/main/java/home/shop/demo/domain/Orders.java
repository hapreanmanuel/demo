package home.shop.demo.domain;

import lombok.Data;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Data
@Entity
@Table(name="ORDERS")
public class Orders {

//    @ManyToOne
//    @JoinColumn(name="EMPLOYEEID",referencedColumnName = "EMPLOYEEID")
//    private Employee employee;
//
//    @ManyToOne
//    @JoinColumn(name="CUSTOMERID")
//    private Customer customer;
//
//    @OneToMany
//    @JoinColumn(name="ORDERID")
//    private List<Orderdetails> orderdetails = new ArrayList<>();

    @Id
    @Column(name = "ORDERID", nullable = true)
//    @GeneratedValue(strategy=GenerationType.AUTO)
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


    //Accepted values for status are:

    // - 'new'        -> the order is being created. The client decides a whishlist.
    //                   orders with status 'new' have not been added to the Orders table yet.
    // - 'processing' -> the order is currently in process. The client has submitted the request and the order needs
    //                   to be handled by an employee. In this state the products have not been shipped yet.
    //                   in this state the order can be cancelled.
    // - 'cancelled'  -> the order was cancelled by the user and does not need further processing.
    //                   The order and all corresponding orderdetails are deleted
    // - 'sent'       -> the order has been executed and the products left the storages

    //Default value when creating a new order is -> 'processing'
    @Basic
    @Column(name="STATUS", nullable=false)
    private String status = "new";                  // default value when creating an order


}

