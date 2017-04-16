package com.payroll.schedules;

import com.payroll.domain.Schedule;

import java.time.LocalDate;

import static java.time.LocalDate.MIN;

public class MonthlySchedule implements Schedule {

    private LocalDate lastPayDate = MIN;

    @Override
    public boolean isPayDay(LocalDate date) {
        return lastPayDate.plusMonths(1).isBefore(date) &&
                date.getMonth().length(date.isLeapYear()) == date.getDayOfMonth();
    }

    public void setLastPayDay(LocalDate lastPayDate) {
        this.lastPayDate = lastPayDate;
    }
}
