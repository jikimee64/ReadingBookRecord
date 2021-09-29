package com.soap.objects.chapter14noconsistency;

import com.soap.objects.chapter10.bad.Money;
import com.soap.objects.chapter14noconsistency.BasicPolicy.FixedFeePolicy;
import java.time.Duration;

/**
 * 구간별 방식
 */
public class DurationDiscouutRule extends FixedFeePolicy {
    private Duration from;
    private Duration to;

    public DurationDiscouutRule(Money amount, Duration seconds, Duration from, Duration to) {
        super(amount, seconds);
        this.from = from;
        this.to = to;
    }

    public Money calculate(Call call){
        if (call.getDuration().compareTo(to) > 0){
            return Money.ZERO;
        }

        if(call.getDuration().compareTo(from) < 0){
            return Money.ZERO;
        }

        Phone phone = new Phone(null);
        phone.call(new Call(call.getFrom().plus(from),
                        call.getDuration().compareTo(to) > 0 ? call.getFrom().plus(to) : call.getTo()));
        return super.calculateFee(phone);
    }

}
