package home.shop.demo.controller;

import home.shop.demo.domain.*;
import home.shop.demo.service.CustomerService;
import home.shop.demo.service.OrderdetailsService;
import home.shop.demo.service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private OrdersService ordersService;

    @Autowired
    private OrderdetailsService orderdetailsService;


    public CustomerController(CustomerService customerService){
        this.customerService =customerService;
    }

    @GetMapping("/list")
    public Iterable<Customer> list() {
        return customerService.getAllCustomers();
    }

    @GetMapping("/{customerId}")
    public Customer getCustomer(@PathVariable("customerId") String customerId){ return customerService.getCustomer(customerId); }

    @GetMapping("/{customerId}/orders")
    public List<Orders> getCustomerOrders(@PathVariable("customerId") String customerId) { return customerService.getCustomer(customerId).getClientOrders() ;}

    @PostMapping("/{customerId}/newOrder")
    public Orders createOrderAsCustomer(@PathVariable("customerId") String customerId) {
        Orders newOrder = ordersService.newOrderForCustomer(customerId);
        ordersService.addOrder(newOrder);
        return newOrder;
    }

    @PostMapping("/{customerId}/{orderId}/newOrderDetails")
    public void addOrderdetailsToOrder(@RequestBody OrderdetailsRequestBody orderdetailsRequestBody, @PathVariable("customerId") String customerId, @PathVariable("orderId") int orderId){
        orderdetailsService.addOrderdetails(orderId, orderdetailsRequestBody.getProductId(), orderdetailsRequestBody.getQuantity());
    }

    @PostMapping("/{customerId}/{orderId}/addDeliveryInfo")
    public void addOrderdetailsShippment(@RequestBody ShippmentDetails shippmentDetails, @PathVariable("orderId") int orderId){
        ordersService.updateOrderdetailsShippment(ordersService.getOrder(orderId),shippmentDetails);
    }

    //TODO - extra logic to block changes for the order after submition
    //TODO - unit testing
    @PostMapping("/{customerId}/{orderId}/submit")
    public void submitOrder(@PathVariable("customerId") String customerId, @PathVariable("orderId") int orderId){
        customerService.submitOrder(ordersService.getOrder(orderId), customerId);
    }


}
