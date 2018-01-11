package home.shop.demo;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import home.shop.demo.domain.*;
import home.shop.demo.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;


@SpringBootApplication
@EntityScan("home.shop.demo")
@EnableJpaRepositories("home.shop.demo")
public class DemoApplication {


	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

}

// TODO - refactoring @component CleanStateDataLoader with function calls for each repo

//Issues : table 'ORDERDETAILS' has a collumn 'orderID' (PK). shouldn't it have a corresponding entry in
//          table 'ORDERS'? (with the same key ???)
//         table 'ORDERDETAILS' has a collumn 'productID'. shouldn't it be a FK for table 'Product'?
//

//This component reads data from .json files for tables: PRODUCT, PRODUCTCATEGORY, CUSTOMER, EMPLOYEE, ORDERS and ORDERDETAILS
@Component
class CleanStateDataLoader implements CommandLineRunner {

	private final Logger logger = LoggerFactory.getLogger(CleanStateDataLoader.class);

	@Autowired
	private CustomerService customerService;

	@Autowired
	private EmployeeService employeeService;

	@Autowired
	private OrderdetailsService orderdetailsService;

	@Autowired
	private OrdersService ordersService;

	@Autowired
	private ProductService productService;

	@Autowired
	private ProductcategoryService productcategoryService;

	//Read date from JSON files and update values in database
	@Override
	public void run(String... strings) throws Exception {
		logger.info("Loading clean state data");

		//Object mapper for entry association
		ObjectMapper mapper = new ObjectMapper();

		//Type references for each entity
		TypeReference<List<Customer>> customerTypeRef = new TypeReference<List<Customer>>() {};

		TypeReference<List<Employee>> employeeTypeRef = new TypeReference<List<Employee>>() {};

		TypeReference<List<Orderdetails>> orderdetailsTypeRef = new TypeReference<List<Orderdetails>>() {};

		TypeReference<List<Orders>> ordersTypeRef = new TypeReference<List<Orders>>(){};

		TypeReference<List<Product>> productTypeRef = new TypeReference<List<Product>>() {};

		TypeReference<List<ProductCategory>> productcategoryTypeRef = new TypeReference<List<ProductCategory>>() {};


		//Input stream file for each entity
		InputStream customerInputStream = TypeReference.class.getResourceAsStream("/json_data/Customer.json");

		InputStream employeeInputStream = TypeReference.class.getResourceAsStream("/json_data/Employee.json");

		InputStream orderdetailsInputStream = TypeReference.class.getResourceAsStream("/json_data/OrderDetails.json");

		InputStream ordersInputStream = TypeReference.class.getResourceAsStream("/json_data/Orders.json");

		InputStream productInputStream = TypeReference.class.getResourceAsStream("/json_data/Product.json");

		InputStream productcategoryInputStream = TypeReference.class.getResourceAsStream("/json_data/ProductCategory.json");


		//Logic blocks (IO Exception is thrown by file reading operation)
		try {
			List<Customer> customers = mapper.readValue(customerInputStream, customerTypeRef);
			customerService.save(customers);
			logger.info("Customers saved to db");
		} catch (IOException e) {
			logger.info("Unable to save customers: " + e.getMessage());
		}

		try {
			List<Employee> employees = mapper.readValue(employeeInputStream, employeeTypeRef);
			employeeService.save(employees);
			logger.info("Emlpoyees saved to db");
		} catch (IOException e) {
			logger.info("Unable to save emlpoyees: " + e.getMessage());
		}

		try {
			List<ProductCategory> productCategories = mapper.readValue(productcategoryInputStream, productcategoryTypeRef);
			productcategoryService.save(productCategories);
			logger.info("Product categories saved to db");
		} catch (IOException e) {
			logger.info("Unable to save product categories: " + e.getMessage());
		}

		try {
			List<Product> products = mapper.readValue(productInputStream, productTypeRef);
			productService.save(products);
			logger.info("Products saved to db");
		} catch (IOException e) {
			logger.info("Unable to save products: " + e.getMessage());
		}

		try {
			List<Orders> orders = mapper.readValue(ordersInputStream, ordersTypeRef);

			//change status of orders read from JSON to 'processing'
			orders.forEach(order -> order.setStatus("processing"));

			ordersService.save(orders);
			logger.info("Orders saved to db");
		} catch (IOException e) {
			logger.info("Unable to save orders: " + e.getMessage());
		}


		try {
			List<Orderdetails> orderdetails = mapper.readValue(orderdetailsInputStream, orderdetailsTypeRef);
			orderdetailsService.save(orderdetails);
			logger.info("Order details saved to db");
		} catch (IOException e) {
			logger.info("Unable to save order details: " + e.getMessage());
		}


	}
}

@Component
class TableInitializations implements CommandLineRunner {

	private final Logger logger = LoggerFactory.getLogger(CleanStateDataLoader.class);

	@Autowired
	LocationService locationService;

	@Autowired
	ProductStockService productStockService;

	@Autowired
	ProductService productService;

	//Read date from JSON files and update values in database
	@Override
	public void run(String... strings) throws Exception {
		logger.info("Creating 2 locations and adding them to LOCATION table");

		//Add two hardcoded locations in 'Location' table
		Location mainLocation = new Location();
		Location secondLocation = new Location();

		mainLocation.setLocationId("mainLocation");
		mainLocation.setLocationAddress("St January Street 1812");
		mainLocation.setLocationCity("Cluj-Napoca");
		mainLocation.setLocationCountry("Romania");
		mainLocation.setLocationPostalCode("511800");
		mainLocation.setLocationRegion("CJ");

		secondLocation.setLocationId("secondLocation");
		secondLocation.setLocationAddress("St February Street 22B");
		secondLocation.setLocationCity("Bucuresti");
		secondLocation.setLocationCountry("Romania");
		secondLocation.setLocationPostalCode("511200");
		secondLocation.setLocationRegion("B");

		locationService.addLocation(mainLocation);
		locationService.addLocation(secondLocation);

		//Create stocks for each location
		List<ProductStock> mainLocationStock = new ArrayList<ProductStock>();
		List<ProductStock> secondLocationStock = new ArrayList<ProductStock>();

		int mainLocationQuantityForEachProduct = 1000;
		int secondLocationQuantityForEachProduct = 2000;

		productService.getAllProducts().forEach(product -> {
			mainLocationStock.add(new ProductStock(mainLocation.getLocationId(),product.getProductId(), mainLocationQuantityForEachProduct ));
			secondLocationStock.add(new ProductStock(secondLocation.getLocationId(),product.getProductId(), secondLocationQuantityForEachProduct ));
		});

		productStockService.save(mainLocationStock);
		productStockService.save(secondLocationStock);


	}
}

