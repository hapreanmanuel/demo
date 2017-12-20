package home.shop.demo;

import models.Product;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {

	@Test
	public void contextLoads() {

		EntityManagerFactory factory = Persistence.createEntityManagerFactory("PersistenceUnit");
		EntityManager myManager = factory.createEntityManager();
		assertNotNull(myManager);


		myManager.getTransaction().begin();
		Product firstProduct = new Product("HT-testproduct");

		System.out.println(firstProduct.getProductId());

		myManager.persist(firstProduct);
		myManager.getTransaction().commit();

		myManager.close();


	}

}
