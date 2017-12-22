package home.shop.demo.domain;

import lombok.Data;

import javax.persistence.*;


@Data
@Entity
@Table(name="EMPLOYEE")
public class Employee {
    @Id
    @Column(name="EMPLOYEEID")
    private int employeeId;

    @Basic
    @Column(name="FIRSTNAME")
    private String firstName;

    @Basic
    @Column(name="LASTNAME")
    private String lastName;

    @Basic
    @Column(name="TITLE")
    private String title;

    @Basic
    @Column(name="HOMEPHONE")
    private String homePhone;

    @Basic
    @Column(name="PHOTO")
    private String photo;


}
