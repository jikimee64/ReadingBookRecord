package com.soap.objects.chapter5.discountcondition;

import com.soap.objects.chapter5.Screening;
import java.time.DayOfWeek;
import java.time.LocalTime;

public class PeriodCondition implements DiscountCondition {
    private DayOfWeek dayOfWeek; //요일
    private LocalTime startTime; //상영시작시간
    private LocalTime endTime; //상영종료시간

    public PeriodCondition(DayOfWeek dayOfWeek, LocalTime startTime, LocalTime endTime) {
        this.dayOfWeek = dayOfWeek;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    @Override
    public boolean isSatisfiedBy(Screening screening) {
        return dayOfWeek.equals(screening.getWhenScreened().getDayOfWeek()) &&
            startTime.compareTo(screening.getWhenScreened().toLocalTime()) <= 0 &&
            this.endTime.compareTo(screening.getWhenScreened().toLocalTime()) >= 0;
    }
}
