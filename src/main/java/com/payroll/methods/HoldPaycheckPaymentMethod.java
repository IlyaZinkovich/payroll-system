package com.payroll.methods;

import com.payroll.domain.Paycheck;
import com.payroll.domain.PaymentMethod;

public class HoldPaycheckPaymentMethod implements PaymentMethod {

    @Override
    public Paycheck pay(Integer amount) {
        return new Paycheck(amount);
    }
}
