package com.soap.objects.chapter11.bad;

import com.soap.objects.chapter10.bad.Money;
import java.time.Duration;

public class RateDiscountableAndTaxableNightlyDiscountPhone extends RateDiscountableNightlyDiscountPhone{

    private double taxRate;

    public RateDiscountableAndTaxableNightlyDiscountPhone(
        Money nightlyAmount,
        Money regularAmount, Duration seconds) {
        super(nightlyAmount, regularAmount, seconds);
    }

    @Override
    protected Money afterCalculated(Money fee) {
        return super.afterCalculated(fee).plus(fee.times(taxRate));
    }

}
