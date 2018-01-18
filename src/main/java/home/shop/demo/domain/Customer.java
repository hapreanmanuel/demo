package home.shop.demo.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name="CUSTOMER")
public class Customer {

    @OneToMany(orphanRemoval = true)
    @JoinColumn(name="CUSTOMERID")
    private List<Orders> clientOrders;

    @Id
    @Column(name="CUSTOMERID")
    private String customerID;

    @Basic
    @Column(name="COMPANYNAME")
    private String companyName;

    @Transient
    private Orders currentOrder;
}
