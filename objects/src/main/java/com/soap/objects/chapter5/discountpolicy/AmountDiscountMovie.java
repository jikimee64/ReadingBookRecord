package com.soap.objects.chapter5.discountpolicy;

import com.soap.objects.chapter5.discountcondition.DiscountCondition;
import com.soap.objects.chapter5.Money;
import com.soap.objects.chapter5.Movie;
import java.time.Duration;

public class AmountDiscountMovie extends Movie {

    private Money discountAmount; //할인 금액

    public AmountDiscountMovie(String title, Duration runningTime,
        Money fee, Money discountAmount,
        DiscountCondition... discountConditions) {
        super(title, runningTime, fee, discountConditions);
        this.discountAmount = discountAmount;
    }

    @Override
    protected Money calculateDiscountAmount() {
        return discountAmount;
    }
}
