package com.payroll.domain;

import java.time.LocalDate;

public interface Schedule {

    boolean isPayDay(LocalDate date);
    void setLastPayDay(LocalDate lastPayDate);
}
