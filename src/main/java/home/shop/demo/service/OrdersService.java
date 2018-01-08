package home.shop.demo.service;

import home.shop.demo.domain.Orders;
import home.shop.demo.repository.OrdersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrdersService {

    @Autowired
    private OrdersRepository ordersRepository;

    public OrdersService(OrdersRepository ordersRepository){
        this.ordersRepository = ordersRepository;
    }

    public Iterable<Orders> getAllOrders() {
        return ordersRepository.findAll();
    }

    public Orders getOrder(int orderId) {
        return ordersRepository.findOne(orderId);
    }

    public void addOrder(Orders order){
        ordersRepository.save(order);
    }

    public void deleteOrder(int orderId) {
        ordersRepository.delete(orderId);
    }

    public void save(List<Orders> orders) {
        ordersRepository.save(orders);
    }
}
