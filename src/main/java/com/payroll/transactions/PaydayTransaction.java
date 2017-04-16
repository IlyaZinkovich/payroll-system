package com.payroll.transactions;

import com.payroll.database.PayrollDatabase;
import com.payroll.domain.Paycheck;
import com.payroll.domain.Transaction;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class PaydayTransaction implements Transaction {

    private LocalDate date;
    private PayrollDatabase payrollDatabase;
    private Map<Integer, Paycheck> paychecks = new HashMap<>();

    public PaydayTransaction(LocalDate date, PayrollDatabase payrollDatabase) {
        this.date = date;
        this.payrollDatabase = payrollDatabase;
    }

    @Override
    public void execute() {
        payrollDatabase.getEmployees()
                .forEach(employee -> paychecks.put(employee.getId(), employee.payday(date)));
    }

    public Paycheck getPaycheck(Integer employeeId) {
        return paychecks.get(employeeId);
    }
}
