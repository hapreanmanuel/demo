package home.shop.demo.service;

import home.shop.demo.domain.Employee;
import home.shop.demo.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public Iterable<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public Employee getEmployee(int employeeID) {
        return employeeRepository.findOne(employeeID);
    }

    public void addEmployee(Employee employee) {
        employeeRepository.save(employee);
    }

    public void deleteEmployee(int employeeID) {
        employeeRepository.delete(employeeID);
    }

    public void save(List<Employee> employeeList) {
        employeeRepository.save(employeeList);
    }

}
