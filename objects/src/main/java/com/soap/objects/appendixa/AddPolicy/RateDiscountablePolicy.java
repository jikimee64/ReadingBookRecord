package com.soap.objects.appendixa.AddPolicy;

import com.soap.objects.appendixa.RatePolicy;
import com.soap.objects.chapter10.bad.Money;

public class RateDiscountablePolicy extends AdditionalRatePolicy {

    private Money discountAmount;

    public RateDiscountablePolicy(Money discountAmount, RatePolicy next) {
        super(next);
        this.discountAmount = discountAmount;
    }

    @Override
    protected Money afterCalculated(Money fee) {
        return fee.minus(discountAmount);
    }

}