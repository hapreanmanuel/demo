package home.shop.demo;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import home.shop.demo.domain.Customer;
import home.shop.demo.service.CustomerService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@SpringBootApplication
@EntityScan("home.shop.demo")
@EnableJpaRepositories("home.shop.demo")
public class DemoApplication {


	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Bean
	CommandLineRunner runner(CustomerService customerService){
		return args -> {
			//Read json and write to db
			ObjectMapper mapper = new ObjectMapper();
			TypeReference<List<Customer>> typeReference = new TypeReference<List<Customer>>(){};

			InputStream inputStream = TypeReference.class.getResourceAsStream("/json_data/Customer.json");

			try{
				List<Customer> customers = mapper.readValue(inputStream, typeReference);
				customerService.save(customers);
				System.out.println("Customers saved to db");

			} catch(IOException e){
				System.out.println("Unable to save customers: " + e.getMessage());
			}


		};
	}

}
