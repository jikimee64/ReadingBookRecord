package com.soap.objects.chapter11.bad;

import com.soap.objects.chapter10.bad.Money;
import java.time.Duration;

public class TaxableAndRateDIscountableRegularPhone extends TaxableRegularPhone{
    private Money discountAmount;

    public TaxableAndRateDIscountableRegularPhone(Money amount,
        Duration seconds,
        double taxRate) {
        super(amount, seconds, taxRate);
    }

    @Override
    protected Money afterCalculated(Money fee){
        return super.afterCalculated(fee).minus(discountAmount);
    }

}