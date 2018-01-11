package home.shop.demo.service;

import home.shop.demo.domain.Orders;
import home.shop.demo.domain.ShippmentDetails;
import home.shop.demo.repository.OrdersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;

@Service
public class OrdersService {

    private int numberOfDaysForRequiredOrder = 14;

    @Autowired
    private OrdersRepository ordersRepository;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private EmployeeService employeeService;

    public OrdersService(OrdersRepository ordersRepository){
        this.ordersRepository = ordersRepository;
    }

    public Iterable<Orders> getAllOrders() {
        return ordersRepository.findAll();
    }

    public Orders getOrder(int orderId) {
        return ordersRepository.findOne(orderId);
    }

    public void addOrder(Orders order){ ordersRepository.save(order); }

    public void updateOrder(Orders order){ ordersRepository.save(order); }

    public void deleteOrder(int orderId) {
        ordersRepository.delete(orderId);
    }

    public void save(List<Orders> orders) { ordersRepository.save(orders); }

    public int generateUniqueId(){
        int newId = 1;
        while(ordersRepository.findOne(newId) != null){
            newId++;
        }
        return newId;
    }

    //Method to generate a new order for a specified customer
    //This method sets the required fields for an order which are not user-input
    public Orders newOrderForCustomer(String customerId) {
        Orders newOrderForCustomer = new Orders();
        newOrderForCustomer.setOrderId(generateUniqueId());     //Set unique identification
        newOrderForCustomer.setCustomerId(customerId);
        newOrderForCustomer.setCustomerName(customerService.getCustomer(customerId).getCompanyName());
        newOrderForCustomer.setOrderDate(new Timestamp(System.currentTimeMillis()).toString());

        Calendar cal = Calendar.getInstance();
        cal.setTime(Timestamp.valueOf(newOrderForCustomer.getOrderDate()));
        cal.add(Calendar.DAY_OF_WEEK, numberOfDaysForRequiredOrder);

        newOrderForCustomer.setRequiredDate(new Timestamp(cal.getTime().getTime()).toString());

        newOrderForCustomer.setEmployeeId(employeeService.getMostAvailableEmployee().getEmployeeId());

        return newOrderForCustomer;
    }

    public void updateOrderdetailsShippment(Orders order, ShippmentDetails shippmentDetails) {
        order.setFreight(shippmentDetails.getFreight());
        order.setShipAddress(shippmentDetails.getShipAddress());
        order.setShipCity(shippmentDetails.getShipCity());
        order.setShipCountry(shippmentDetails.getShipCountry());
        order.setShipName(shippmentDetails.getShipName());
        order.setShipPostalCode(shippmentDetails.getShipPostalCode());
        order.setShipRegion(shippmentDetails.getShipRegion());
        order.setShipVia(shippmentDetails.getShipVia());
        ordersRepository.save(order);
    }

    public List<Orders> getOrdersForEmployee(int employeeId){
        return ordersRepository.findAllByEmployeeId(employeeId);
    }

    public void setOrderStatus_processing(int orderId){
        if(getOrder(orderId).getStatus().equals("new")) {
            getOrder(orderId).setStatus("processing");
            updateOrder(getOrder(orderId));
        }
    }

    public void setOrderStatus_cancelled(int orderId){
        if(getOrder(orderId).getStatus().equals("processing")){
            getOrder(orderId).setStatus("cancelled");
            updateOrder(getOrder(orderId));
        }
        else{
            // Display error message
        }
    }

    public void setOrderStatus_sent(int orderId){
        if(getOrder(orderId).getStatus().equals("processing")){
            getOrder(orderId).setStatus("sent");
            updateOrder(getOrder(orderId));
        }
        else{
            // Display error message
        }
    }
}
