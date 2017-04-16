package com.payroll.domain;

public interface PaymentMethod {

    Paycheck pay(Integer amount);
}
