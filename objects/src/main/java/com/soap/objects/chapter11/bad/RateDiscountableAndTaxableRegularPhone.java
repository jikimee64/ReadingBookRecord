package com.soap.objects.chapter11.bad;

import com.soap.objects.chapter10.bad.Money;
import java.time.Duration;

public class RateDiscountableAndTaxableRegularPhone extends RateDiscountableRegularPhone{
    private double taxRate;

    public RateDiscountableAndTaxableRegularPhone(Money amount,
        Duration seconds, Money discountAMount) {
        super(amount, seconds, discountAMount);
    }

    @Override
    protected Money afterCalculated(Money fee) {
        return super.afterCalculated(fee).plus(fee.times(taxRate));
    }

}