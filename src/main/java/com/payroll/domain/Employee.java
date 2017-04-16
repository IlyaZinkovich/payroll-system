package com.payroll.domain;

import java.time.LocalDate;

public class Employee {

    private Integer id;
    private String name;
    private Schedule schedule;
    private PaymentClassification paymentClassification;
    private PaymentMethod paymentMethod;

    public Employee(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Schedule getSchedule() {
        return schedule;
    }

    public PaymentClassification getPaymentClassification() {
        return paymentClassification;
    }

    public void setSchedule(Schedule schedule) {
        this.schedule = schedule;
    }

    public void setPaymentClassification(PaymentClassification paymentClassification) {
        this.paymentClassification = paymentClassification;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public Paycheck payday(LocalDate date) {
        if (schedule.isPayDay(date)) {
            Integer amount = paymentClassification.calculatePay(date);
            Paycheck paycheck = paymentMethod.pay(amount);
            schedule.setLastPayDay(date);
            return paycheck;
        }
        return null;
    }
}
