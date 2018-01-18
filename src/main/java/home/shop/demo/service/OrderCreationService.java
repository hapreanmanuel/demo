package home.shop.demo.service;

import home.shop.demo.domain.OrderCreationObject;
import home.shop.demo.domain.OrderStatus;
import home.shop.demo.domain.Orders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderCreationService {

//    •	You get a single java object as input. This object will contain the order timestamp, the delivery address and a list of products (product ID and quantity) contained in the order.
//            •	You return an Order entity if the operation was successful. If not, you throw an exception.
//            •	The service has to select a strategy for finding from which locations should the products be taken. See the strategy design pattern. The strategy should be selected based on a spring boot configuration. The following strategies should be created:
//    o	Single location: find a single location that has all the required products (with the required quantities) in stock.
//    o	Most “abundant” locations: for each required product, select the location that has the largest stock for that given product.
//    o	Greedy: take from each location as many products as possible.
//•	The service then runs the strategy, obtaining a list of object with the following structure: location, product, quantity (= how many items of the given product are taken from the given location). If the strategy is unable to find a suitable set of locations, it should throw an exception.
//            •	The stocks need to be updated by subtracting the shipped goods.
//•	Afterwards the order is persisted in the database and returned.
//
    @Autowired
    OrdersService ordersService;

    @Autowired
    OrderdetailsService orderdetailsService;

    //The order is created and stored in the table.
    public Orders createOrder(OrderCreationObject orderCreationObject) {
        Orders newOrder = ordersService.newOrderForCustomer(orderCreationObject.getCustomer().getCustomerID());

//        newOrderForCustomer.setCustomerId(customerId);
//        newOrderForCustomer.setCustomerName(customerService.getCustomer(customerId).getCompanyName());
//        newOrderForCustomer.setOrderDate(new Timestamp(System.currentTimeMillis()).toString());

//        Calendar cal = Calendar.getInstance();
//        cal.setTime(Timestamp.valueOf(newOrderForCustomer.getOrderDate()));
//        cal.add(Calendar.DAY_OF_WEEK, numberOfDaysForRequiredOrder);

//        newOrderForCustomer.setRequiredDate(new Timestamp(cal.getTime().getTime()).toString());
//
//        newOrderForCustomer.setEmployeeId(employeeService.getMostAvailableEmployee().getEmployeeId());

//        //Set currentOrder to this one. If the user logs out without submitting, this order will be lost.
//        customerService.getCustomer(customerId).setCurrentOrder(newOrderForCustomer);

        //User input data
        newOrder.setShipAddress(orderCreationObject.getFullAddress());
        newOrder.setShipCity(orderCreationObject.getCity());
        newOrder.setShipCountry(orderCreationObject.getCountry());
        newOrder.setShipPostalCode(orderCreationObject.getPostalCode());
        newOrder.setShipRegion(orderCreationObject.getRegion());

        //TODO - location selection strategy -> this should also determine the shipvia and freight



        newOrder.setOrderId(ordersService.generateUniqueId());

        //Add orderId to orderdetails List
        orderCreationObject.getOrderdetailsList().forEach(orderdetails -> orderdetails.setOrderId(newOrder.getOrderId()));


        newOrder.setStatus(OrderStatus.PROCESSING);

        //Save order to table
        ordersService.addOrder(newOrder);
        // Save order details to table
        orderdetailsService.save(orderCreationObject.getOrderdetailsList());

        return newOrder;
    }
}
