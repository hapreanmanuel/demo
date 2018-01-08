package home.shop.demo.service;

import home.shop.demo.domain.Orderdetails;
import home.shop.demo.repository.OrderdetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderdetailsService {

    @Autowired
    private OrderdetailsRepository orderdetailsRepository;

    public OrderdetailsService(OrderdetailsRepository orderdetailsRepository){
        this.orderdetailsRepository = orderdetailsRepository;
    }


    public Iterable<Orderdetails> getAllOrderdetails() {
        return orderdetailsRepository.findAll();
    }

    public Orderdetails getOrderdetails(int orderId) {
        return orderdetailsRepository.findOne(orderId);
    }

    public void addOrderdetails(Orderdetails orderdetails) {
        orderdetailsRepository.save(orderdetails);
    }

    public void deleteOrderdetails(int orderId) {
        orderdetailsRepository.delete(orderId);
    }

    public void save(List<Orderdetails> orderdetailsList) {
        orderdetailsRepository.save(orderdetailsList);
    }
}
