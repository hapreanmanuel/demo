package home.shop.demo.controller;

import home.shop.demo.domain.Customer;
import home.shop.demo.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    public CustomerController(CustomerService customerService){
        this.customerService =customerService;
    }

    @GetMapping("/list")
    public Iterable<Customer> list() {
        return customerService.getAllCustomers();
    }


}
