package home.shop.demo.repository;

import home.shop.demo.domain.Orderdetails;
import home.shop.demo.domain.OrderdetailsKey;
import org.aspectj.weaver.ast.Or;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderdetailsRepository extends JpaRepository<Orderdetails, Integer> {

    Orderdetails findOneByOrderIdAndProductId(int orderId, String productId);

    List<Orderdetails> findAllByOrderId(int orderId);

    List<Orderdetails> findAllByProductId(String productId);

//    Orderdetails findByOrderdetailsKey(OrderdetailsKey orderdetailsKey);
//
////    Orderdetails findOne(OrderdetailsKey orderdetailsKey);
//
//    List<Orderdetails> findAllByOrderdetailsKey_OrderId(int orderId);
//
//    List<Orderdetails> findAllByOrderdetailsKey_ProductId(String productId);

}
