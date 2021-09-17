package com.soap.objects.chapter11.bad;

import com.soap.objects.chapter10.bad.Money;
import java.time.Duration;

public class RateDiscountableRegularPhone extends RegularPhone{
    private Money discountAMount;

    public RateDiscountableRegularPhone(Money amount, Duration seconds,
        Money discountAMount) {
        super(amount, seconds);
        this.discountAMount = discountAMount;
    }

    @Override
    protected Money afterCalculated(Money fee){
        return fee.minus(discountAMount);
    }

}