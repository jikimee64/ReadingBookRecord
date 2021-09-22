package com.soap.objects.appendixa;

import com.soap.objects.chapter10.bad.Money;

public class Bill {
    private Phone phone;
    private Money fee;

    public Bill(Phone phone, Money fee) {
        if(phone == null){
            throw new IllegalArgumentException();
        }
        if(fee.isLessThan(Money.ZERO)){
            throw new IllegalArgumentException();
        }
        this.phone = phone;
        this.fee = fee;
    }
}
