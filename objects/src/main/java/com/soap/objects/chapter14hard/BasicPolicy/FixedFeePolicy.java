package com.soap.objects.chapter14hard.BasicPolicy;

import com.soap.objects.chapter10.bad.Call;
import com.soap.objects.chapter10.bad.Money;
import com.soap.objects.chapter11.goodcomposition.BasicPolicy.BasicRatePolicy;
import java.time.Duration;

public class FixedFeePolicy extends BasicRatePolicy {

    private Money amount;
    private Duration seconds;

    public FixedFeePolicy(Money amount, Duration seconds) {
        this.amount = amount;
        this.seconds = seconds;
    }

    @Override
    protected Money calculateCallFee(Call call) {
        return amount.times(call.getDuration().getSeconds() / seconds.getSeconds());
    }

}