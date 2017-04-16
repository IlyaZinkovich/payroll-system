package com.payroll.transactions;

import com.payroll.database.PayrollDatabase;
import com.payroll.domain.Employee;
import com.payroll.domain.Transaction;
import com.payroll.transactions.DeleteEmployeeTransaction;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.assertNull;

@RunWith(JUnit4.class)
public class DeleteEmployeeTransactionTest {

    private PayrollDatabase payrollDatabase;

    @Before
    public void setUp() {
        payrollDatabase = new PayrollDatabase();
    }

    @Test
    public void deletesEmployee() {
        Integer employeeId = 1;
        String employeeName = "Bob";
        Employee employee = new Employee(employeeId, employeeName);
        payrollDatabase.addEmployee(employeeId, employee);
        Transaction deleteEmployeeTransaction = new DeleteEmployeeTransaction(employeeId, payrollDatabase);

        deleteEmployeeTransaction.execute();

        assertNull(payrollDatabase.getEmployee(employeeId));
    }
}
