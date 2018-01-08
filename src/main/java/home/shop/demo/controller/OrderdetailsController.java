package home.shop.demo.controller;

import home.shop.demo.domain.Orderdetails;
import home.shop.demo.service.OrderdetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orderdetails")
public class OrderdetailsController {
    @Autowired
    private OrderdetailsService orderdetailsService;

    public OrderdetailsController(OrderdetailsService orderdetailsService){
        this.orderdetailsService = orderdetailsService;
    }

    @GetMapping("/list")
    public Iterable<Orderdetails> list(){
        return orderdetailsService.getAllOrderdetails();
    }
}
