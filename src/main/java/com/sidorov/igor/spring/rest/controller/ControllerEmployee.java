package com.sidorov.igor.spring.rest.controller;

import com.sidorov.igor.spring.rest.ExceptionHandling.EmployeeIncorrectData;
import com.sidorov.igor.spring.rest.ExceptionHandling.NoSuchEmployeeException;
import com.sidorov.igor.spring.rest.Service.EmployeeService;
import com.sidorov.igor.spring.rest.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ControllerEmployee {
    @Autowired
    private EmployeeService employeeService;

    @GetMapping(value = "/employees")
    public List<Employee> showAllEmployee() {
        return employeeService.getAllEmployees();
    }

    @GetMapping(value = "/employees/{id}")
    public Employee getEmployee(@PathVariable int id) {
        Employee employee = employeeService.getEmployee(id);
        if(employee == null) {
            throw new NoSuchEmployeeException("There is no employee with ID = " +
                    id + " in Data Base");
        }
        return employee;
    }

    @PostMapping(value = "/employees")
    public Employee addNewEmployee(@RequestBody Employee employee) {
        employeeService.saveEmployee(employee);
        return employee;
    }

    @PutMapping(value = "/employees")
    public Employee updateEmployee(@RequestBody Employee employee) {
        employeeService.saveEmployee(employee);
        return employee;
    }

    @DeleteMapping(value = "/employees/{id}")
    public String deleteEmployee(@PathVariable int id) {
        Employee employee = employeeService.getEmployee(id);
        if(employee == null) throw new NoSuchEmployeeException(String.format("There is no employee with ID = " +
                                                                "%d in Data Base", id));
        employeeService.deleteEmployee(id);
        return String.format("Employee with id = %d was deleted", id);
    }
}
