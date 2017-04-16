package com.payroll.transactions;

import com.payroll.database.PayrollDatabase;
import com.payroll.domain.*;

public abstract class AddEmployeeTransaction implements Transaction {

    private Integer employeeId;
    private String employeeName;
    private PaymentMethod paymentMethod;
    private PayrollDatabase payrollDatabase;

    public AddEmployeeTransaction(Integer employeeId, String employeeName, PaymentMethod paymentMethod,
                                  PayrollDatabase payrollDatabase) {
        this.employeeId = employeeId;
        this.employeeName = employeeName;
        this.paymentMethod = paymentMethod;
        this.payrollDatabase = payrollDatabase;
    }

    @Override
    public void execute() {
        Employee employee = new Employee(employeeId, employeeName);
        employee.setPaymentClassification(getPaymentClassification());
        employee.setSchedule(getSchedule());
        employee.setPaymentMethod(getPaymentMethod());
        payrollDatabase.addEmployee(employee.getId(), employee);
    }

    protected abstract PaymentClassification getPaymentClassification();

    protected abstract Schedule getSchedule();

    protected PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }
}
