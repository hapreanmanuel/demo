package home.shop.demo.service;

import home.shop.demo.domain.Employee;
import home.shop.demo.domain.OrderStatus;
import home.shop.demo.domain.Orders;
import home.shop.demo.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private OrdersService ordersService;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public Iterable<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public Employee getEmployee(int employeeID) { return employeeRepository.findOne(employeeID); }

    public void addEmployee(Employee employee) {
        employeeRepository.save(employee);
    }

    public void deleteEmployee(int employeeID) {
        employeeRepository.delete(employeeID);
    }

    public void save(List<Employee> employeeList) { employeeRepository.save(employeeList); }

    public List<Orders> getOrdersByEmployeeId(int employeeId) { return employeeRepository.findOne(employeeId).getAssignedOrders();}

    //Find the employee with the least orders associated
    public Employee getMostAvailableEmployee() {
        Employee mostAvailableEmployee = employeeRepository.findAll().get(0);


        for(Employee employee : employeeRepository.findAll()) {

            if(employee.getAssignedOrders().size() < mostAvailableEmployee.getAssignedOrders().size()){
                mostAvailableEmployee = employee;
            }
      }
        return mostAvailableEmployee;
    }

    //Process an assigned order
    public void processOrder(Orders orderToProcess, Employee employee){
        if(orderToProcess.getEmployeeId() == employee.getEmployeeId()){
            //Order process actions.
            //Validation, select location from which to send the products, create billing, update stocks

            //Update order status
            orderToProcess.setStatus(OrderStatus.SENT);

            //Presist changes
            ordersService.updateOrder(orderToProcess);
        }
        else{
            //Print error messages
        }

    }

//    //Method used by the user to canel an order.
//    public void cancelOrder(Orders orderToCancel, String customerId) {
//        if(orderToCancel.getStatus()== OrderStatus.PROCESSING  && orderToCancel.getCustomerId().equals(customerId)){
//            //Cancel-specific actions
//            orderToCancel.setStatus(OrderStatus.PROCESSING);            //set status to processing
//
//            //Add the order to the database
//            ordersService.updateOrder(orderToCancel);
//        }
//        else{
//            //Print error messages
//        }
//    }

}
