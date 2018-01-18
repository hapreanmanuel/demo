package home.shop.demo;


import home.shop.demo.domain.*;

import home.shop.demo.repository.CustomerRepository;
import home.shop.demo.service.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

//Test for repositories
@RunWith(SpringRunner.class)
@DataJpaTest
@ComponentScan("home.shop.demo")
public class JpaRepositoriesTest {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    CustomerService customerService;

    @Autowired
    ProductService productService;

    //This test creates a new Customer entity, adds it to the repository and then deletes it.
    @Test
    public void customerRepoTest(){

        Customer newCustomer = new Customer();
        newCustomer.setCustomerID("Test Customer");
        newCustomer.setCompanyName("Testing Company CO");

        Iterable<Customer> customers = customerRepository.findAll();

        assertThat(customerRepository.findOne(newCustomer.getCustomerID())).isNull();       //user does not belong to repo

        customerRepository.save(newCustomer);

        assertThat(customerRepository.findOne(newCustomer.getCustomerID())).isEqualTo(newCustomer);

        customerRepository.delete(newCustomer);

        assertThat(customerRepository.findOne(newCustomer.getCustomerID())).isNull();

    }


    //This test creates 2 new Customer entities, adds them to the repo via 'CustomerService' and then deletes them.
    @Test
    public void customerServiceTest(){

        //Test repo methods acessed from the 'CustomerService' class
        Customer c1 = new Customer();
        Customer c2 = new Customer();

        c1.setCustomerID("ServiceTestUser1");
        c1.setCompanyName("Self Employed");

        c2.setCustomerID("ServiceTestUser2");
        c2.setCompanyName("Also Self Employed");

        List<Customer> newCustomerList = Arrays.asList(c1,c2);

        assertThat(customerService.getCustomer(c1.getCustomerID())).isNull();       // c1 does not belong to the list

        customerService.save(newCustomerList);                                      //Save a list of entries

        assertThat(customerService.getAllCustomers()).containsAll(newCustomerList);     //The service contains c1 and c2

        customerService.deleteCustomer(c2.getCustomerID());

        assertThat(customerService.getCustomer(c2.getCustomerID())).isNull();       // c2 does not belong to the list any more

        customerService.deleteCustomer(c1.getCustomerID());

        assertThat(customerService.getAllCustomers()).doesNotContainAnyElementsOf(newCustomerList); // users have been deleted

    }

    @Autowired
    OrdersService ordersService;

    @Test
    public void orderServiceTest() {
        //Test that the orderService updates an entry instead of creating a new one
        Orders order = new Orders();

        order.setOrderId(ordersService.generateUniqueId());

        ordersService.addOrder(order);

        System.out.println(ordersService.getOrder(order.getOrderId()));

        order.setCustomerName("Joshua");

        ordersService.addOrder(order);

        System.out.println(ordersService.getOrder(order.getOrderId()));

        //Test unique order ID generation method

//        for(int i = 1; i<=1000;i++){
//            Orders order = new Orders();
//            order.setOrderId(ordersService.generateUniqueId());
//            ordersService.addOrder(order);
//            System.out.println(order.getOrderId());
//        }

    }


    @Autowired
    OrderdetailsService orderdetailsService;

    //Test out the methods for the orderdetailsService class

//    get one by orderId and productId
//    get one by OrderdetailsKey class
//    get all by order Id
//    get all by product Id
//    delete all by orderId
    @Test
    public void orderdetailsServiceTest() {

        //Hardcoded values for existing entries
        int dummyOrderId = 7918;                // this order has 4 orderdetails entities associated
        String dummyProductId = "HT-1256";           // this product is present in 3 orderdetails
        //orderdetails with dummyOrderId and dummyProductId is unique

        System.out.println("All orders: ");
        //Write all orders
        orderdetailsService.getAllOrderdetails().forEach(orderdetails -> System.out.println(orderdetails.toString()));

        System.out.println("Orders with orderId: " + dummyOrderId + " and productId: " + dummyProductId);
        //Print entry given by dummy values
        System.out.println(orderdetailsService.getOrderdetails(dummyOrderId,dummyProductId).toString());

        System.out.println("Orders with orderdetailsKey:( " + dummyOrderId + ", " + dummyProductId+")");
        //Again but with 'OrderdetailsKey' entity
        System.out.println(orderdetailsService.getOrderdetails(dummyOrderId,dummyProductId).toString());

        System.out.println("Orders with orderId: " + dummyOrderId);
        //All orders with dummyOrderId
        orderdetailsService.getOrderdetailsByOrderId(dummyOrderId).forEach(orderdetails -> System.out.println(orderdetails.toString()));

        //Check there are 4 entries for the specified order
        assertThat(orderdetailsService.getOrderdetailsByOrderId(dummyOrderId).size()).isEqualTo(4);

        System.out.println("Orders with productId: " + dummyProductId);
        //All orders with dummyProductId
        orderdetailsService.getOrderdetailsByProductId(dummyProductId).forEach(orderdetails -> System.out.println(orderdetails.toString()));

        //Check there are 3 entries for the specified order
        assertThat(orderdetailsService.getOrderdetailsByProductId(dummyProductId).size()).isEqualTo(3);

        //Delete all orders with dummyOrderId
        orderdetailsService.deleteOrderdetailsByOrderId(dummyOrderId);

        System.out.println("Orders with orderId: " + dummyOrderId);
        //All orders with dummyOrderId -> none should exist any more
        orderdetailsService.getOrderdetailsByOrderId(dummyOrderId).forEach(orderdetails -> System.out.println(orderdetails.toString()));

        //Check there are 4 entries for the specified order
        assertThat(orderdetailsService.getOrderdetailsByOrderId(dummyOrderId)).isEmpty();

    }


    @Autowired
    EmployeeService employeeService;

    @Test
    public void employeeServiceTest() {
        //Employee 7840 has 2 associated orders (2686 and 6858)
        int dummyEmployeeId = 7840;

        System.out.println(employeeService.getEmployee(dummyEmployeeId));

        List<Orders> dummyEmployeeAssociatedOrders = employeeService.getOrdersByEmployeeId(dummyEmployeeId);

        assertThat(dummyEmployeeAssociatedOrders).isNotEmpty();     //list should have entries

        dummyEmployeeAssociatedOrders.forEach(orders -> System.out.println(orders.toString()));

        //Test the find most availalbe employee algorithm
        Employee emp = employeeService.getMostAvailableEmployee();

    }

    @Autowired
    LocationService locationService;

    @Autowired
    ProductStockService productStockService;

    //The data is added to the locations table via the component 'TableInitializations'
    @Test
    public void tableInitializationsTest() {
       //Check if the two locations have been added to the table
        List<Location> locations = locationService.getAllLocations();
        assertThat(locations.size()).isEqualTo(2);
        locations.forEach(location -> System.out.println(location.toString()));

        //Check if stocks have been created
        assertThat(productStockService.getAllProductStocks()).isNotEmpty();
        productStockService.deleteStockForLocation("mainLocation");
        productStockService.deleteStockForLocation("secondLocation");
        assertThat(productStockService.getAllProductStocks()).isEmpty();


    }

    @Test
    public void printCurrentTimestamp(){
        Timestamp now = new Timestamp(System.currentTimeMillis());
        System.out.println("Now: " + now.toString());

        Calendar cal = Calendar.getInstance();
        cal.setTime(now);
        cal.add(Calendar.DAY_OF_WEEK, 14);

        Timestamp nextWeek = new Timestamp(cal.getTime().getTime());

        System.out.println("14 Days from now: " + nextWeek);


    }

    //In dev phase, two locations have been created.
    //The first location has 1000 pieces for each product and the second location has 2000 pieces for each product
    //This test verifies if the total stock calculation method works correctly (expected 3000 pieces for any product)
    @Test
    public void productStocksTest(){

        List<ProductStock> totalProductStock = productStockService.totalProductStocks();

        assertThat(totalProductStock.stream().findFirst().get().getQuantity()).isEqualTo(3000);

        totalProductStock.forEach(productStock -> System.out.println(productStock.toString()));

    }

    @Autowired
    ShopService shopService;

    //This test checks the getAvailableStockForLocation of the ShopService class
    @Test
    public void testAvailableStocksRetrieval(){

        ProductStock p1 = new ProductStock();   p1.setProductId("HT-1001");
        ProductStock p2 = new ProductStock();   p2.setProductId("HT-1600");
        ProductStock p3 = new ProductStock();   p3.setProductId("HT-8000");


        //Dummy shoppingCart
        List<ProductStock> dummyShoppingCart = Arrays.asList(p1, p2, p3);


        List<ProductStock> availableAtMain = shopService.getAvailableStockForLocation(dummyShoppingCart, locationService.getLocation("mainLocation"));

        availableAtMain.forEach(productStock -> System.out.println(productStock.toString()));

        assertThat(availableAtMain).size().isEqualTo(3);

    }


}