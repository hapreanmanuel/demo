package home.shop.demo.service;

import home.shop.demo.domain.Orderdetails;
import home.shop.demo.repository.OrderdetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class OrderdetailsService {

    @Autowired
    private OrderdetailsRepository orderdetailsRepository;

    @Autowired
    private ProductService productService;

    public OrderdetailsService(OrderdetailsRepository orderdetailsRepository){
        this.orderdetailsRepository = orderdetailsRepository;
    }

    public List<Orderdetails> getAllOrderdetails() {
        return orderdetailsRepository.findAll();
    }

//    public Orderdetails getOrderdetails(OrderdetailsKey orderdetailsKey) {
//        return orderdetailsRepository.findOneByOrderIdAndProductId(orderdetailsKey.getOrderId(), orderdetailsKey.getProductId());
//    }

    public Orderdetails getOrderdetails(int orderId, String productId) {
        return orderdetailsRepository.findOneByOrderIdAndProductId(orderId,productId);
    }

    public List<Orderdetails> getOrderdetailsByOrderId(int orderId){
        return orderdetailsRepository.findAllByOrderId(orderId);
    }

    public List<Orderdetails> getOrderdetailsByProductId(String productId){
        return orderdetailsRepository.findAllByProductId(productId);
    }

    private void addOrderdetails(Orderdetails orderdetails) {
        orderdetailsRepository.save(orderdetails);
    }

    public void save(List<Orderdetails> orderdetailsList) {
        orderdetailsRepository.save(orderdetailsList);
    }

    public void deleteOrderdetails(Orderdetails orderdetails) {
        orderdetailsRepository.delete(orderdetails);
    }

    //Deletes all orders which have the given orderId
    public void deleteOrderdetailsByOrderId(int orderId) { orderdetailsRepository.delete(orderdetailsRepository.findAllByOrderId(orderId)); }

    //New orderdetails object
    public void addOrderdetails(int orderId, String productId, int quantity){
        Orderdetails newOrderdetails = new Orderdetails();
        newOrderdetails.setOrderId(orderId);
        newOrderdetails.setProductId(productId);
        newOrderdetails.setQuantity(quantity);
        newOrderdetails.setUnitPrice(productService.getProduct(productId).getPrice());
        newOrderdetails.setDiscount(calculateDiscount());
        addOrderdetails(newOrderdetails);
    }

    private float calculateDiscount(){
        Random r = new Random();
        return r.nextFloat()*15;
    }

}
