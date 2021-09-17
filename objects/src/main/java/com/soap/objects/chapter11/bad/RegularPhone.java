package com.soap.objects.chapter11.bad;

import com.soap.objects.chapter10.bad.Call;
import com.soap.objects.chapter10.bad.Money;

import java.time.Duration;

public class RegularPhone extends Phone {
    private Money amount; //단위 요금
    private Duration seconds; //단위시간

    public RegularPhone(Money amount, Duration seconds) {
        this.amount = amount;
        this.seconds = seconds;
    }

    @Override
    protected Money calculateCallFee(Call call){
        return amount.times(call.getDuration().getSeconds() / seconds.getSeconds());
    }

}