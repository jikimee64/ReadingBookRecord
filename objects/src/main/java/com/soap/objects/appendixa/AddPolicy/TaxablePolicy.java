package com.soap.objects.appendixa.AddPolicy;

import com.soap.objects.appendixa.RatePolicy;
import com.soap.objects.chapter10.bad.Money;

//세금정책
public class TaxablePolicy extends AdditionalRatePolicy {

    private double taxRatio;

    public TaxablePolicy(double taxRatio, RatePolicy next) {
        super(next);
        this.taxRatio = taxRatio;
    }

    @Override
    protected Money afterCalculated(Money fee) {
        return fee.plus(fee.times(taxRatio));
    }

}