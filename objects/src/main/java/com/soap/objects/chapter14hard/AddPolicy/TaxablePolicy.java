package com.soap.objects.chapter14hard.AddPolicy;

import com.soap.objects.chapter10.bad.Money;
import com.soap.objects.chapter11.goodcomposition.RatePolicy;

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