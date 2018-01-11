package home.shop.demo.service;

import home.shop.demo.domain.Employee;
import home.shop.demo.domain.Orders;
import home.shop.demo.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
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

    //    System.out.println("1st Employee: " + mostAvailableEmployee.getEmployeeId() + " has " + mostAvailableEmployee.getAssignedOrders().size() + " assigned orders.");

        for(Employee employee : employeeRepository.findAll()) {
    //        System.out.println("Current Employee: " + employee.getEmployeeId() + " has " + employee.getAssignedOrders().size() + " assigned orders.");

            if(employee.getAssignedOrders().size() < mostAvailableEmployee.getAssignedOrders().size()){
                mostAvailableEmployee = employee;
            }
      }
    //    System.out.println("Most availalbe Employee: " + mostAvailableEmployee.getEmployeeId() + " has " + mostAvailableEmployee.getAssignedOrders().size() + " assigned orders.");

        return mostAvailableEmployee;
    }

}
