package com.soap.objects.chapter14noconsistency.BasicPolicy;

import com.soap.objects.chapter10.bad.Money;
import com.soap.objects.chapter14noconsistency.BasicRatePolicy;
import com.soap.objects.chapter14noconsistency.Call;
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