package com.payroll.transactions;

import com.payroll.classifications.SalariedClassification;
import com.payroll.database.PayrollDatabase;
import com.payroll.transactions.AddEmployeeTransaction;
import com.payroll.transactions.AddSalariedEmployeeTransaction;
import com.payroll.methods.HoldPaycheckPaymentMethod;
import com.payroll.domain.Employee;
import com.payroll.domain.PaymentMethod;
import com.payroll.schedules.MonthlySchedule;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static junit.framework.TestCase.assertEquals;

@RunWith(JUnit4.class)
public class AddSalariedEmployeeTransactionTest {

    private PayrollDatabase payrollDatabase;

    @Before
    public void setUp() {
        payrollDatabase = new PayrollDatabase();
    }

    @Test
    public void addsEmployee() {
        Integer employeeId = 1;
        String employeeName = "Bob";
        Integer salary = 1000;
        PaymentMethod paymentMethod = new HoldPaycheckPaymentMethod();

        AddEmployeeTransaction addEmployeeTransaction =
                new AddSalariedEmployeeTransaction(employeeId, employeeName, salary, paymentMethod, payrollDatabase);

        addEmployeeTransaction.execute();

        Employee employee = payrollDatabase.getEmployee(employeeId);
        assertEquals(employeeId, employee.getId());
        assertEquals(employeeName, employee.getName());
        assertEquals(MonthlySchedule.class, employee.getSchedule().getClass());
        assertEquals(paymentMethod, employee.getPaymentMethod());
        assertEquals(SalariedClassification.class, employee.getPaymentClassification().getClass());
    }
}
