package home.shop.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;

//Test for order processing (employee)
@RunWith(SpringRunner.class)
@DataJpaTest
@ComponentScan("home.shop.demo")
public class OrderProcessingTest {

    //An employee needs to process an order
    @Test
    public void processOrder(){

    }

    /*
    Scenarios:
        - order is not assigned to the given user
        - order exceeds the current stock capacity
        - order processed correctly

     */

}
