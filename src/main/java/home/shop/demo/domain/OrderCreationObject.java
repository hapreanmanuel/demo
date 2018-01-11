package home.shop.demo.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.List;

//This object is created by the user ('customer') via the GUI
//No data is saved to the database until the 'Orders' entity is created.

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderCreationObject {

    private Customer customer;                          //the  customer which creates the order

    //This field is not a user input ; the employee is assigned by a availability algorithm
    private Employee employee;                          //the employee assigned to the order

    private Timestamp orderCreation;                    //moment of submitting the order

    private Timestamp orderRequired;                    //required time of delivery

    private List<Orderdetails> orderdetailsList;        //shopping cart

    //Address fields
    private String fullAddress;

    private String city;

    private String country;

    private String postalCode;

    private String region;
}


