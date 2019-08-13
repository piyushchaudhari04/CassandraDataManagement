package com.example.practice.CassandraDataManipulation.service;

import com.example.practice.CassandraDataManipulation.domain.Employee;

import java.util.List;

public interface EmployeeService {

    /**
     * Used to Create the Employee Information
     * @param employee
     * @return {@link Employee}
     */
    Employee createEmployee(Employee employee);

    /**
     * Getting the Employee Information using Id
     * @param id
     * @return {@link Employee}
     */
    Employee getEmployee(int id);

    /**
     * Used to Update the Employee Information
     * @param employee
     * @return {@link Employee}
     */

    Employee updateEmployee(Employee employee);

    /**
     * Deleting the Employee Information using Id
     * @param id
     */
    void deleteEmployee(int id);

    /**
     * Getting the All Employees information
     * @return
     */
    List<Employee> getAllEmployees();
}
