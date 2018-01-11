package home.shop.demo.service;

import home.shop.demo.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;


@Service
public class ShopService {


    @Autowired
    private OrdersService ordersService;
//
//    public ProductService(ProductRepository productRepository) {
//        this.productRepository = productRepository;
//    }
//
//    public Iterable<Product> getAllProducts() {
//        return productRepository.findAll();
//    }
//
//    public Product getProduct(String productId) {
//        return productRepository.findOne(productId);
//    }
//
//    public void addProduct(Product product) {
//        productRepository.save(product);
//    }
//
//    public void deleteProduct(String productId) {
//        productRepository.delete(productId);
//    }
//
//    public void save(List<Product> products) {
//        productRepository.save(products);
//    }

    @Autowired
    CustomerService customerService;

    @Autowired
    EmployeeService employeeService;

    //Create OrderCreationObject
    public OrderCreationObject newOrderCreationObject(String customerId, int employeeId, List<Orderdetails> orderdetailsList, String fullAddress, String city, String country, String postalCode, String region){

        OrderCreationObject orderCreationObject = new OrderCreationObject();

        orderCreationObject.setCustomer(customerService.getCustomer(customerId));
        orderCreationObject.setEmployee(employeeService.getEmployee(employeeId));
        orderCreationObject.setOrderCreation(new Timestamp(System.currentTimeMillis()));

        //Default required date for delivery is 14 days after placing the order
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(orderCreationObject.getOrderCreation().getTime());
        cal.add(Calendar.DAY_OF_MONTH, 14);
        orderCreationObject.setOrderRequired(new Timestamp(cal.getTime().getTime()));

        orderCreationObject.setOrderdetailsList(orderdetailsList);
        orderCreationObject.setFullAddress(fullAddress);
        orderCreationObject.setCity(city);
        orderCreationObject.setCountry(country);
        orderCreationObject.setPostalCode(postalCode);
        orderCreationObject.setRegion(region);

        return orderCreationObject;
    }


    //Method to create new orders from the 'OrderCreationObject' entity
    public void createOrder(OrderCreationObject orderCreationObject) {
        Orders order = new Orders();
        order.setOrderId(ordersService.generateUniqueId());
        order.setCustomerId(orderCreationObject.getCustomer().getCustomerID());
        order.setCustomerName(orderCreationObject.getCustomer().getCompanyName());
        order.setEmployeeId(orderCreationObject.getEmployee().getEmployeeId());



        order.setOrderDate(orderCreationObject.getOrderCreation().toString());

        //Logic goes here

        ordersService.addOrder(order);
    }




}

