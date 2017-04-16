package com.payroll.domain;

import java.time.LocalDate;

public interface PaymentClassification {

    Integer calculatePay(LocalDate date);
}
