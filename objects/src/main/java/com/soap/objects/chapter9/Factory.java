package com.soap.objects.chapter9;

import com.soap.objects.chapter2.AmountDiscountPolicy;
import com.soap.objects.chapter2.Money;
import com.soap.objects.chapter2.Movie;
import com.soap.objects.chapter2.PeriodCondition;
import com.soap.objects.chapter2.SequenceCondition;
import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalTime;
import org.springframework.stereotype.Component;

@Component
public class Factory {

    //Pure Fabrication
    public Movie createAvatarMovie(){
        return new Movie("아바타",
            Duration.ofMinutes(120),
            Money.wons(10000),
            new AmountDiscountPolicy(Money.wons(800),
                new SequenceCondition(1),
                new SequenceCondition(10),
                new PeriodCondition(DayOfWeek.MONDAY, LocalTime.of(10, 0), LocalTime.of(11, 59)),
                new PeriodCondition(DayOfWeek.THURSDAY, LocalTime.of(10, 0), LocalTime.of(20, 59))
            ));
    }

}