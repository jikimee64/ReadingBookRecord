package com.soap.objects.chapter14noconsistency;

import com.soap.objects.chapter10.bad.Money;
import java.time.DayOfWeek;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class DayOfWeekDiscountRule {
    private List<DayOfWeek> dayOfWeeks = new ArrayList<>();
    private Duration duration = Duration.ZERO;
    private Money amount = Money.ZERO;

    public DayOfWeekDiscountRule(List<DayOfWeek> dayOfWeeks, Duration duration,
        Money amount) {
        this.dayOfWeeks = dayOfWeeks;
        this.duration = duration;
        this.amount = amount;
    }

    /**
     * interval이 요일조건을 만족시킬 경우 단위 시간과 단위 요금을 이용해 통화 요금을 계산
     * @param interval
     * @return
     */
    public Money calculate(DateTimeInterval interval){
        if(dayOfWeeks.contains(interval.getFrom().getDayOfWeek())){
            return amount.times(interval.duration().getSeconds() / duration.getSeconds());
        }

        return Money.ZERO;
    }
}
