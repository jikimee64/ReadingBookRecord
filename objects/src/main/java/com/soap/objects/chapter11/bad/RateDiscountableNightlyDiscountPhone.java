package com.soap.objects.chapter11.bad;

import com.soap.objects.chapter10.bad.Money;
import java.time.Duration;

public class RateDiscountableNightlyDiscountPhone extends NightlyDiscountPhone{

    public RateDiscountableNightlyDiscountPhone(Money nightlyAmount,
        Money regularAmount, Duration seconds) {
        super(nightlyAmount, regularAmount, seconds);
    }

    @Override
    protected Money afterCalculated(Money fee) {
        return super.afterCalculated(fee);
    }

}