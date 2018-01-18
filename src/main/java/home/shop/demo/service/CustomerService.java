package home.shop.demo.service;

import home.shop.demo.domain.Customer;
import home.shop.demo.domain.OrderStatus;
import home.shop.demo.domain.Orders;
import home.shop.demo.repository.CustomerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    private final Logger logger = LoggerFactory.getLogger(CustomerService.class);

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private OrdersService ordersService;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Iterable<Customer> getAllCustomers() { return customerRepository.findAll(); }

    public Customer getCustomer(String customerID) { return customerRepository.findOne(customerID);}

    public void addCustomer(Customer customer) { customerRepository.save(customer);}

    public void updateCustomer(Customer customer) {customerRepository.save(customer);}

    public void deleteCustomer(String customerID) { customerRepository.delete(customerID);  }

    public void save(List<Customer> customerList) { customerRepository.save(customerList);  }

    public Customer save(Customer customer) { return customerRepository.save(customer); }

    //Method used by the user to add a new order. The order is only added to the table here.
    public void submitOrder(Orders newOrder, String customerId) {
        if (newOrder.getStatus() == OrderStatus.NEW && newOrder.getCustomerId().equals(customerId)) {
            //Submit-specific actions
            newOrder.setStatus(OrderStatus.PROCESSING);            //set status to processing

            //Add the order to the database
            ordersService.addOrder(newOrder);
        } else {
            logger.info("Submit unsuccessful.");
        }
    }

    //Method used by the user to cancel an order.
    public void cancelOrder(Orders orderToCancel, String customerId) {
        if (orderToCancel.getStatus() == OrderStatus.PROCESSING && orderToCancel.getCustomerId().equals(customerId)) {
            //Cancel-specific actions
            orderToCancel.setStatus(OrderStatus.CANCELLED);            //set status to processing

            //Update the order to the database
            ordersService.updateOrder(orderToCancel);
        } else {
            logger.info("Cancel unsuccessful");
        }
    }

}
