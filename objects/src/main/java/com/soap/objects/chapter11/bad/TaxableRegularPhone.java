package com.soap.objects.chapter11.bad;

import com.soap.objects.chapter10.bad.Money;
import java.time.Duration;

public class TaxableRegularPhone extends RegularPhone {
    private double taxRate;

    public TaxableRegularPhone(Money amount, Duration seconds,
        double taxRate) {
        super(amount, seconds);
        this.taxRate = taxRate;
    }

    @Override
    protected Money afterCalculated(Money fee){
        return fee.plus(fee.times(taxRate));
    }
}
