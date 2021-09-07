package com.soap.objects.chapter6.commandquery;

import java.time.Duration;
import java.time.LocalDateTime;

public class Event {

    private String subject;
    private LocalDateTime from;
    private Duration duration;

    public Event(String subject, LocalDateTime from, Duration duration) {
        this.subject = subject;
        this.from = from;
        this.duration = duration;
    }

    //쿼리
    //요일, 시작시간, 소요시간 판단
    public boolean isSatisfied(RecurringSchedule schedule) {
        if (from.getDayOfWeek() != schedule.getDayOfWeek() ||
            !from.toLocalTime().equals(schedule.getFrom()) ||
            !duration.equals(schedule.getDuration())) {
            //reschedule(schedule); //잘못된 위치
            return false;
        }
        return true;
    }

    //명령
    public void reschedule(RecurringSchedule schedule) {
        from = LocalDateTime.of(from.toLocalDate().plusDays(daysDistance(schedule)),
            schedule.getFrom());
        duration = schedule.getDuration();
    }

    private long daysDistance(RecurringSchedule schedule) {
        return schedule.getDayOfWeek().getValue() - from.getDayOfWeek().getValue();
    }
}
