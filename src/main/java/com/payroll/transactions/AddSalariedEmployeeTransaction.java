package com.payroll.transactions;

import com.payroll.classifications.SalariedClassification;
import com.payroll.database.PayrollDatabase;
import com.payroll.domain.PaymentClassification;
import com.payroll.domain.PaymentMethod;
import com.payroll.domain.Schedule;
import com.payroll.schedules.MonthlySchedule;

public class AddSalariedEmployeeTransaction extends AddEmployeeTransaction {

    private Integer salary;

    public AddSalariedEmployeeTransaction(Integer employeeId, String employeeName, Integer salary,
                                          PaymentMethod paymentMethod, PayrollDatabase payrollDatabase) {
        super(employeeId, employeeName, paymentMethod, payrollDatabase);
        this.salary = salary;
    }

    @Override
    protected PaymentClassification getPaymentClassification() {
        return new SalariedClassification(salary);
    }

    @Override
    protected Schedule getSchedule() {
        return new MonthlySchedule();
    }
}
