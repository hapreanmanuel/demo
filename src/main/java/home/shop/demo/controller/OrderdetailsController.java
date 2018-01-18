package home.shop.demo.controller;

import home.shop.demo.domain.Orderdetails;
import home.shop.demo.service.OrderdetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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

    @GetMapping("/{orderId}")
    public List<Orderdetails> orderdetailsByOrderId(@PathVariable("orderId") int orderId) {
        return orderdetailsService.getOrderdetailsByOrderId(orderId);
    }

    @GetMapping("/byproduct/{productId}")
    public List<Orderdetails> orderdetailsByProductId(@PathVariable("productId") String productId) {
        return orderdetailsService.getOrderdetailsByProductId(productId);
    }

}
