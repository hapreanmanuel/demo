package home.shop.demo;

import javafx.application.Application;
import models.Product;
import org.hibernate.jpa.boot.spi.EntityManagerFactoryBuilder;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.LocalEntityManagerFactoryBean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.activation.DataSource;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

@SpringBootApplication
@EntityScan("home.shop.demo")
@EnableJpaRepositories("home.shop.demo")
public class DemoApplication {

//	@Autowired
//	private static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("PersistenceUnit");
//
//
//	private ApplicationContext applicationContext;
//
//	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException{
//		this.applicationContext = applicationContext;
//	}
//
//
//
//	@Bean
//	@ConfigurationProperties(prefix="spring.datasource")
//	public DataSource dataSource(){
//		return (DataSource) DataSourceBuilder.create().build();
//	}
//
//
//	@Bean
//	public EntityManagerFactoryBuilder productEntityManagerFactory(
//			EntityManagerFactoryBuilder builder) {
//		return (EntityManagerFactoryBuilder) builder
//				.withDataSource((javax.sql.DataSource) dataSource())
//				.build();
//	}


	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}


}




//@Bean
//public EntityManagerFactoryBuilder productEntityManagerFactory(
//		EntityManagerFactoryBuilder builder) {
//	return builder
//			.withDataSource((javax.sql.DataSource) dataSource())
//			.packages(Product.class)
//			.persistenceUnit("customers")
//			.build();
//}






//@RestController
//class DBController {
//	@Autowired
//	ShopRepository shopRepository;
//
//	@RequestMapping("/save")
//	public String process(){
//		shopRepository.save(new Product("HT-1001"));
//		shopRepository.save(new Product("HT-1002"));
//		return "Done";
//	}
//}
//
//
//
//interface ShopRepository extends JpaRepository<Product, String> {
//	Product findProductBy(String productId);
//}
//

