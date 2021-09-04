package com.soap.objects.chapter5.discountpolicy;

import com.soap.objects.chapter5.Money;
import com.soap.objects.chapter5.Movie;
import com.soap.objects.chapter5.discountcondition.DiscountCondition;
import java.time.Duration;

public class PercentDiscountMovie extends Movie {

    private double discountPercent; //할인 비율

    public PercentDiscountMovie(String title, Duration runningTime,
        Money fee, double discountPercent, DiscountCondition... discountConditions) {
        super(title, runningTime, fee, discountConditions);
        this.discountPercent = discountPercent;
    }

    @Override
    protected Money calculateDiscountAmount() {
        return getFee().times(discountPercent);
    }
}
