package com.soap.objects.chapter11.goodcomposition.AddPolicy;

import com.soap.objects.chapter10.bad.Money;
import com.soap.objects.chapter11.goodcomposition.RatePolicy;

public class RateDiscountablePolicy extends AdditionalRatePolicy{

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