package com.soap.objects.object10.good;

import com.soap.objects.object10.bad.Call;
import com.soap.objects.object10.bad.Money;
import java.time.Duration;

public class RegularPhone extends Phone {
    private Money amount; //단위 요금
    private Duration seconds; //단위시간

    public RegularPhone(Money amount, Duration seconds, double taxRate) {
        super(taxRate);
        this.amount = amount;
        this.seconds = seconds;
    }

    public Money getAmount() {
        return amount;
    }

    public Duration getSeconds() {
        return seconds;
    }


    @Override
    protected Money calculateCallFee(Call call){
        return amount.times(call.getDuration().getSeconds() / seconds.getSeconds());
    }

}