package com.soap.objects.chapter14good.policy;

import com.soap.objects.chapter14good.Call;
import com.soap.objects.chapter14good.DateTimeInterval;
import com.soap.objects.chapter14good.fee.FeeCondition;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

public class TimeOfDayFeeCondition implements FeeCondition {
    private LocalTime from;
    private LocalTime to;

    public TimeOfDayFeeCondition(LocalTime from, LocalTime to) {
        this.from = from;
        this.to = to;
    }

    /**
     * 날짜별로 시간 간격을 분할한 후 from과 to 사이의 시간대를 구한다.
     */
    @Override
    public List<DateTimeInterval> findTimeIntervals(Call call) {
        return call.getInterval().splitByDay()
            .stream()
            .map(each ->
                DateTimeInterval.of(
                    LocalDateTime.of(each.getFrom().toLocalDate(), from(each)),
                    LocalDateTime.of(each.getTo().toLocalDate(), to(each)))
            )
            .collect(Collectors.toList());
    }

    private LocalTime from(DateTimeInterval interval) {
        return interval.getFrom().toLocalTime().isBefore(from) ?
                from : interval.getFrom().toLocalTime();
    }

    private LocalTime to(DateTimeInterval interval){
        return interval.getTo().toLocalTime().isAfter(to) ?
                    to : interval.getTo().toLocalTime();
    }

}