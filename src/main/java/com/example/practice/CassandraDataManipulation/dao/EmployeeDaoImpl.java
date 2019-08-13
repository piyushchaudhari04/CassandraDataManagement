package com.example.practice.CassandraDataManipulation.dao;

import com.example.practice.CassandraDataManipulation.domain.Employee;
import com.example.practice.CassandraDataManipulation.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EmployeeDaoImpl implements EmployeeDAO{

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public Employee createEmployee(Employee employee) {
        return employeeRepository.create(employee);
    }

    @Override
    public Employee getEmployee(int id) {
        return employeeRepository.findById(id,Employee.class);
    }

    @Override
    public Employee updateEmployee(Employee employee) {
        return employeeRepository.update(employee);
    }

    @Override
    public void deleteEmployee(int id) {
        employeeRepository.deleteById(id, Employee.class);
    }

    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll(Employee.class);
    }
}
