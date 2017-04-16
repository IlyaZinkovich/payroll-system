package com.payroll.transactions;

import com.payroll.classifications.SalariedClassification;
import com.payroll.database.PayrollDatabase;
import com.payroll.domain.Employee;
import com.payroll.domain.Paycheck;
import com.payroll.methods.HoldPaycheckPaymentMethod;
import com.payroll.schedules.MonthlySchedule;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.time.LocalDate;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNull;

@RunWith(JUnit4.class)
public class PaydayTransactionTest {

    private PayrollDatabase payrollDatabase;

    @Before
    public void setUp() {
        payrollDatabase = new PayrollDatabase();
        int employeeId = 1;
        Employee employee = new Employee(employeeId, "Bob");
        employee.setPaymentClassification(new SalariedClassification(1000));
        employee.setSchedule(new MonthlySchedule());
        employee.setPaymentMethod(new HoldPaycheckPaymentMethod());
        payrollDatabase.addEmployee(employeeId, employee);
    }

    @Test
    public void paysSalary() {
        PaydayTransaction paydayTransaction =
                new PaydayTransaction(LocalDate.of(2016, 1, 31), payrollDatabase);

        paydayTransaction.execute();

        Paycheck paycheck = paydayTransaction.getPaycheck(1);
        assertEquals(new Integer(1000), paycheck.getAmount());
    }

    @Test
    public void doNotPaySalaryIfDayIsNotLastInMonth() {
        PaydayTransaction paydayTransaction =
                new PaydayTransaction(LocalDate.of(2016, 1, 30), payrollDatabase);

        paydayTransaction.execute();

        Paycheck paycheck = paydayTransaction.getPaycheck(1);
        assertNull(paycheck);
    }

    @Test
    public void doNotPaySalaryTwice() {
        PaydayTransaction paydayTransaction =
                new PaydayTransaction(LocalDate.of(2016, 1, 30), payrollDatabase);

        paydayTransaction.execute();
        paydayTransaction.execute();

        Paycheck paycheck = paydayTransaction.getPaycheck(1);
        assertNull(paycheck);
    }
}
