package com.payroll.transactions;

import com.payroll.database.PayrollDatabase;
import com.payroll.domain.Transaction;

public class DeleteEmployeeTransaction implements Transaction {

    private Integer employeeId;
    private PayrollDatabase payrollDatabase;

    public DeleteEmployeeTransaction(Integer employeeId, PayrollDatabase payrollDatabase) {
        this.employeeId = employeeId;
        this.payrollDatabase = payrollDatabase;
    }

    @Override
    public void execute() {
        payrollDatabase.deleteEmployee(employeeId);
    }
}
