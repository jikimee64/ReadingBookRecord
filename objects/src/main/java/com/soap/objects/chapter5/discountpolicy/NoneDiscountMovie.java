package com.soap.objects.chapter5.discountpolicy;

import com.soap.objects.chapter5.Money;
import com.soap.objects.chapter5.Movie;
import com.soap.objects.chapter5.discountcondition.DiscountCondition;
import java.time.Duration;

public class NoneDiscountMovie extends Movie {

    public NoneDiscountMovie(String title, Duration runningTime,
        Money fee,
        DiscountCondition... discountConditions) {
        super(title, runningTime, fee, discountConditions);
    }

    @Override
    protected Money calculateDiscountAmount() {
        return Money.ZERO;
    }
}
