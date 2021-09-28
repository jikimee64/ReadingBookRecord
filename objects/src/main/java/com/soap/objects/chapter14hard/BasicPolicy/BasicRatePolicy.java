package com.soap.objects.chapter14hard.BasicPolicy;

import com.soap.objects.chapter10.bad.Money;
import com.soap.objects.chapter14hard.Phone;
import com.soap.objects.chapter11.goodcomposition.RatePolicy;
import com.soap.objects.chapter14hard.Call;

//기본 정책
public abstract class BasicRatePolicy implements RatePolicy {

    @Override
    public Money calculateFee(Phone phone) {
        Money result = Money.ZERO;

        for(Call call : phone.getCalls()){
            result.plus(calculateCallFee(call));
        }
        return result;
    }

    protected abstract Money calculateCallFee(Call call);

}
