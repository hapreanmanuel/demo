package home.shop.demo.controller;

import home.shop.demo.domain.Employee;
import home.shop.demo.domain.Orders;
import home.shop.demo.service.EmployeeService;
import home.shop.demo.service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private OrdersService ordersService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/list")
    public Iterable<Employee> list() {
        return employeeService.getAllEmployees();
    }

    //Get all assigned orders for a given employee
    @GetMapping("/{employeeId}/orders")
    public List<Orders> assignedOrdersOfEmployee(@PathVariable("employeeId") int employeeId){
        return ordersService.getOrdersForEmployee(employeeId);
    }

    @GetMapping("/{employeeId}/{orderId}")
    public Orders getOrderOfEmployee(@PathVariable("employeeId") int employeeId, @PathVariable("orderId") int orderId){
        Employee emp = employeeService.getEmployee(employeeId);
        if(emp.getEmployeeId() == employeeId) {
            return ordersService.getOrder(orderId);
        }
        else {
            return null;
        }

    }

    @PostMapping("/{employeeId}/{orderId}/process")
    public void processOrder(@PathVariable("employeeId") int employeeId, @PathVariable("orderId") int orderId){
        Orders order = getOrderOfEmployee(employeeId,orderId);

        ordersService.setOrderStatus_sent(order.getOrderId());

    }

}


