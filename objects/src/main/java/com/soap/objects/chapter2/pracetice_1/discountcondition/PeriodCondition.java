package com.soap.objects.chapter2.pracetice_1.discountcondition;

import com.soap.objects.chapter2.pracetice_1.Reservation;
import java.time.DayOfWeek;
import java.time.LocalTime;
import lombok.AllArgsConstructor;

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