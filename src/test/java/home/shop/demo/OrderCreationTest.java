package home.shop.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;

//Test set for order creation
@RunWith(SpringRunner.class)
@DataJpaTest
@ComponentScan("home.shop.demo")
public class OrderCreationTest {

    //Create a new order for a user
    @Test
    public void CreateOder(){

        //Check if all fields which should be assigned have the proper values

        //Check order status

        //Check if the order can be found from user->orders

    }

    //Try to submit an order with missing fields (proper error messages should be available)

    //Cancel an order


    //Submit order



}
