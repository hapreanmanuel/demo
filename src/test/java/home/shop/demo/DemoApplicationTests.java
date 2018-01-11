package home.shop.demo;

import home.shop.demo.domain.Customer;
import home.shop.demo.domain.OrderCreationObject;
import home.shop.demo.domain.Orders;
import home.shop.demo.repository.CustomerRepository;
import home.shop.demo.service.OrdersService;
import home.shop.demo.service.ProductService;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.assertj.core.api.Assertions.assertThat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {

	@Autowired
	ProductService productService;

	@Autowired
	OrdersService ordersService;

	@Test
	public void contextLoads() {


	}

	@Test
	public void consolePrintShoppingCart(){


		OrderCreationObject orderCreationObject = new OrderCreationObject();

//		SelectedProduct s1 = new SelectedProduct("HT-1001", 2);
//		SelectedProduct s2 = new SelectedProduct("HT-1002",3);
//
//		orderCreationObject.setSelectedProducts(Arrays.asList(s1,s2));
//
//		List<SelectedProduct> selProds = new ArrayList<SelectedProduct>();
//
//		selProds.add(s1);
//		selProds.add(s2);
//
//		orderCreationObject.setSelectedProducts(selProds);
//
//
//		orderCreationObject.getSelectedProducts().forEach(selectedProduct -> {
//
//			System.out.println(productService.getProduct(selectedProduct.getProductId()).toString());
//
//			System.out.println(selectedProduct.toString());
//		});

	}

	@Test
	public void createNewOrderAndPrintOrderId(){
		Orders newOrder = new Orders();
		Orders newOrder2 = new Orders();

		ordersService.addOrder(newOrder);
		ordersService.addOrder(newOrder2);

		System.out.println("New order 1 id: " + newOrder.getOrderId());
		System.out.println("New order 2 id: " + newOrder2.getOrderId());

		ordersService.getAllOrders().forEach(order -> {
			System.out.println("Order id: " + order.getOrderId() + order.toString());
		});

	}

}








