package home.shop.demo.service;

import home.shop.demo.domain.*;
import home.shop.demo.exceptions.InsufficientStockException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class ShopService {

    @Autowired
    private OrdersService ordersService;

    @Autowired
    CustomerService customerService;

    @Autowired
    EmployeeService employeeService;

    @Autowired
    ProductStockService productStockService;

    @Autowired
    LocationService locationService;


    //TODO - Strategy selection
    public List<ProductStock> findBestLocationStrategy(ProductStock shoppingCart) {
        return null;
    }

    public List<ProductStock> singleLocationStrategy(List<ProductStock> shoppingCart) throws InsufficientStockException {

        List<ProductStock> solutionStock = new ArrayList<>();

        return null;
    }

    public List<ProductStock> mostAbundantLocationStrategy(ProductStock shoppingCart) {
        return null;
    }

    public List<ProductStock> greedyStrategy(ProductStock shoppingCart) {
        return null;
    }

    //This method returns the available stocks for the selected products from a specified location
    //      No verifications are made in this method
    public List<ProductStock> getAvailableStockForLocation(List<ProductStock> shoppingCart, Location location){
        return productStockService.getAllProductStocks()
                .stream()
                .filter(productStock -> productStock.getLocationId().equals(location.getLocationId()))       // filter by location
                .filter(productStock -> shoppingCart.stream()
                        .map(ProductStock::getProductId)
                        .collect(Collectors.toList())
                        .contains(productStock.getProductId()))                                         // filter by product id
                .collect(Collectors.toList());
    }


//    //Create OrderCreationObject
//    public OrderCreationObject newOrderCreationObject(String customerId, int employeeId, List<Orderdetails> orderdetailsList, String fullAddress, String city, String country, String postalCode, String region){
//
//        OrderCreationObject orderCreationObject = new OrderCreationObject();
//
//        orderCreationObject.setCustomer(customerService.getCustomer(customerId));
//        orderCreationObject.setEmployee(employeeService.getEmployee(employeeId));
//        orderCreationObject.setOrderCreation(new Timestamp(System.currentTimeMillis()));
//
//        //Default required date for delivery is 14 days after placing the order
//        Calendar cal = Calendar.getInstance();
//        cal.setTimeInMillis(orderCreationObject.getOrderCreation().getTime());
//        cal.add(Calendar.DAY_OF_MONTH, 14);
//        orderCreationObject.setOrderRequired(new Timestamp(cal.getTime().getTime()));
//
//        orderCreationObject.setOrderdetailsList(orderdetailsList);
//        orderCreationObject.setFullAddress(fullAddress);
//        orderCreationObject.setCity(city);
//        orderCreationObject.setCountry(country);
//        orderCreationObject.setPostalCode(postalCode);
//        orderCreationObject.setRegion(region);
//
//        return orderCreationObject;
//    }
//
//
//    //Method to create new orders from the 'OrderCreationObject' entity
//    public void createOrder(OrderCreationObject orderCreationObject) {
//        Orders order = new Orders();
//        order.setOrderId(ordersService.generateUniqueId());
//        order.setCustomerId(orderCreationObject.getCustomer().getCustomerID());
//        order.setCustomerName(orderCreationObject.getCustomer().getCompanyName());
//        order.setEmployeeId(orderCreationObject.getEmployee().getEmployeeId());
//
//
//
//        order.setOrderDate(orderCreationObject.getOrderCreation().toString());
//
//        //Logic goes here
//
//        ordersService.addOrder(order);
//    }
//



}

