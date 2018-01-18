package home.shop.demo.controller;

import home.shop.demo.domain.Orderdetails;
import home.shop.demo.domain.Orders;
import home.shop.demo.exceptions.InsufficientStockException;
import home.shop.demo.service.OrderdetailsService;
import home.shop.demo.service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrdersController {

    @Autowired
    private OrdersService ordersService;

    @Autowired
    private OrderdetailsService orderdetailsService;

    public OrdersController(OrdersService ordersService){
        this.ordersService = ordersService;
    }

    @GetMapping("/list")
    public Iterable<Orders> list() {
        return ordersService.getAllOrders();
    }

    @GetMapping("/{orderId}/details")
    public List<Orderdetails> showDetailsForOrder(@PathVariable("orderId") int orderId) {
        return orderdetailsService.getOrderdetailsByOrderId(orderId);
    }

//    @RequestMapping("/error")
//    public @ResponseBody String throwErrorMessage() throws InsufficientStockException {
//        throw new InsufficientStockException();
//    }

}
