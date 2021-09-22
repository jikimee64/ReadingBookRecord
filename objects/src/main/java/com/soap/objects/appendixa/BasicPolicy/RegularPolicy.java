package com.soap.objects.appendixa.BasicPolicy;

import com.soap.objects.chapter10.bad.Call;
import com.soap.objects.chapter10.bad.Money;
import java.time.Duration;

//일반 요금제
public class RegularPolicy extends BasicRatePolicy {

    private Money amount;
    private Duration seconds;

    public RegularPolicy(Money amount, Duration seconds) {
        this.amount = amount;
        this.seconds = seconds;
    }

    @Override
    protected Money calculateCallFee(Call call){
        return amount.times(call.getDuration().getSeconds() / seconds.getSeconds());
    }

}