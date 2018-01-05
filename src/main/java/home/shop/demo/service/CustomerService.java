package home.shop.demo.service;

import home.shop.demo.domain.Customer;
import home.shop.demo.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerService {



    @Autowired
    private CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Iterable<Customer> getAllCustomers(){
        return customerRepository.findAll();
    }

    public Customer getCustomer(String customerID){
        return customerRepository.findOne(customerID);
    }

    public void addCustomer(Customer customer){
        customerRepository.save(customer);
    }

    public void updateCustomer(Customer customer){
        customerRepository.save(customer);
    }

    public void deleteCustomer(String customerID){
        customerRepository.delete(customerID);
    }



    public void save(List<Customer> customerList){
        customerRepository.save(customerList);
    }
    public Customer save(Customer customer){
        return customerRepository.save(customer);
    }
}
