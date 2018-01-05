package home.shop.demo.domain;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name="CUSTOMER")
public class Customer {

    @Id
    @Column(name="CUSTOMERID")
    private String customerID;

    @Basic
    @Column(name="COMPANYNAME")
    private String companyName;
}
