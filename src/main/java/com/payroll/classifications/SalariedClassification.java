package com.payroll.classifications;

import com.payroll.domain.PaymentClassification;

import java.time.LocalDate;

public class SalariedClassification implements PaymentClassification {

    private Integer salary;

    public SalariedClassification(Integer salary) {
        this.salary = salary;
    }

    @Override
    public Integer calculatePay(LocalDate date) {
        return salary;
    }
}
