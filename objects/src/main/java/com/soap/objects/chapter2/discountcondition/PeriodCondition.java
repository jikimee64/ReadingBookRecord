package com.soap.objects.chapter2.discountcondition;

import com.soap.objects.chapter2.Reservation;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
public class PeriodCondition implements DiscountCondition {
    private DayOfWeek dayOfWeek;
    private LocalTime startPeriodTime;
    private LocalTime endPeriodTime;

    @Override
    public boolean checkCondition(Reservation reservation) {
        return reservation.checkPeriodCondition(dayOfWeek, startPeriodTime, endPeriodTime);
    }

}