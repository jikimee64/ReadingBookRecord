package com.soap.objects.chapter2;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Screening {

    private DayOfWeek dayOfWeek;
    private Integer sequence;
    private LocalTime startTime;
    private LocalTime endTime;

    public boolean checkPeriodCondition(DayOfWeek periodDayOfWeek, LocalTime startPeriodTime, LocalTime endPeriodTime) {
        return startTime.isAfter(startPeriodTime) &&
            startTime.isBefore(endPeriodTime) && (dayOfWeek.equals(periodDayOfWeek));
    }

    public boolean checkSequenceCondition(int sequence) {
        return this.sequence == sequence;
    }

}