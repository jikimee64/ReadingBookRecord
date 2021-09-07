package com.soap.objects.chapter6.commandquery;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalTime;
import lombok.Getter;

@Getter
public class RecurringSchedule {

    private String subject;
    private DayOfWeek dayOfWeek;
    private LocalTime from;
    private Duration duration;

    public RecurringSchedule(String subject, DayOfWeek dayOfWeek, LocalTime from,
        Duration duration) {
        this.subject = subject;
        this.dayOfWeek = dayOfWeek;
        this.from = from;
        this.duration = duration;
    }
}