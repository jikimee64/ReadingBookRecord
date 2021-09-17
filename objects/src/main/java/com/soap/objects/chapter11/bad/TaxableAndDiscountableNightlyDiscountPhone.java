package com.soap.objects.chapter11.bad;

import com.soap.objects.chapter10.bad.Money;
import java.time.Duration;

public class TaxableAndDiscountableNightlyDiscountPhone extends TaxableNightlyDiscountPhone{
    private Money discountAmount;

    public TaxableAndDiscountableNightlyDiscountPhone(
        Money nightlyAmount,
        Money regularAmount, Duration seconds,
        double taxRate) {
        super(nightlyAmount, regularAmount, seconds, taxRate);
    }

    @Override
    protected Money afterCalculated(Money fee){
        return super.afterCalculated(fee).minus(discountAmount);
    }

}
