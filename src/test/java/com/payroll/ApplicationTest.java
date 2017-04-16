package com.payroll;

import com.payroll.database.PayrollDatabase;
import com.payroll.domain.Paycheck;
import com.payroll.transactions.AddSalariedEmployeeTransaction;
import com.payroll.transactions.DeleteEmployeeTransaction;
import com.payroll.transactions.PaydayTransaction;
import com.payroll.methods.HoldPaycheckPaymentMethod;
import org.junit.Test;

import java.time.LocalDate;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;
import static junit.framework.TestCase.assertNull;

public class ApplicationTest {

    @Test
    public void test() {
        PayrollDatabase payrollDatabase = new PayrollDatabase();

        new AddSalariedEmployeeTransaction(1, "Bob", 1000,
                new HoldPaycheckPaymentMethod(), payrollDatabase).execute();
        assertNotNull(payrollDatabase.getEmployee(1));
        PaydayTransaction paydayTransaction = new PaydayTransaction(LocalDate.of(2016, 1, 31), payrollDatabase);
        paydayTransaction.execute();
        Paycheck paycheck = paydayTransaction.getPaycheck(1);
        assertEquals(new Integer(1000), paycheck.getAmount());
        new DeleteEmployeeTransaction(1, payrollDatabase).execute();
        assertNull(payrollDatabase.getEmployee(1));
    }
}
