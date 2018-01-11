package home.shop.demo.repository;

import home.shop.demo.domain.Employee;
import home.shop.demo.domain.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Integer>{


}
