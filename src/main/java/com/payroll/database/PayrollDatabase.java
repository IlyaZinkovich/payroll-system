package com.payroll.database;

import com.payroll.domain.Employee;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class PayrollDatabase {

    private Map<Integer, Employee> employees = new HashMap<>();

    public void addEmployee(Integer employeeId, Employee employee) {
        employees.put(employeeId, employee);
    }

    public Employee getEmployee(Integer employeeId) {
        return employees.get(employeeId);
    }

    public void deleteEmployee(Integer employeeId) {
        employees.remove(employeeId);
    }

    public Collection<Employee> getEmployees() {
        return employees.values();
    }
}
