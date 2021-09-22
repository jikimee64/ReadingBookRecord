package com.soap.objects.appendixa.BasicPolicy;

import com.soap.objects.appendixa.EmptyCallException;
import com.soap.objects.appendixa.Phone;
import com.soap.objects.appendixa.RatePolicy;
import com.soap.objects.chapter10.bad.Call;
import com.soap.objects.chapter10.bad.Money;
import java.util.EmptyStackException;
import java.util.List;

//기본 정책
public abstract class BasicRatePolicy implements RatePolicy {

    @Override
    public Money calculateFee(List<Call> calls) {

        if(calls == null || calls.isEmpty()){
            throw new EmptyCallException();
        }

        //사전 조건
        assert calls != null;

        //사전 조건을 강화할 경우 리스코프 치환 원칙 위반
        //assert !calls.isEmpty();

        //사전 조건 완화(이미 RatePolicy에서 null이 아닌 값만을 받는 사전조건이 있기 때문에 영향 X
//        if(calls == null){
//            return Money.ZERO;
//        }

        Money result = Money.ZERO;

        for(Call call : calls){
            result.plus(calculateCallFee(call));
        }

        //사후 조건
        assert result.isGreaterThanOrEqual(Money.ZERO);

        return result;
    }

    protected abstract Money calculateCallFee(Call call);

}
