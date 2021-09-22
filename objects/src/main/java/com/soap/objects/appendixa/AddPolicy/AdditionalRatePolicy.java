package com.soap.objects.appendixa.AddPolicy;

import com.soap.objects.appendixa.Phone;
import com.soap.objects.appendixa.RatePolicy;
import com.soap.objects.chapter10.bad.Call;
import com.soap.objects.chapter10.bad.Money;
import java.util.List;

//추상 클래스는 인터페이스의 메소드를 직접 구현 안해도됨 > 해당 추상클래스를 상속받는 자식클래스가 구현해야됨
public abstract class AdditionalRatePolicy implements RatePolicy {
    private RatePolicy next;

    public AdditionalRatePolicy(RatePolicy next) {
        changeNext(next);
    }

    //private인 RatePolicy를 자식 클래스에서 변경하는 방법
    protected void changeNext(RatePolicy next){
        this.next = next;
        //불변식
        assert next != null;
    }

    @Override
    public Money calculateFee(List<Call> calls){
        //사전 조건
        assert calls != null;

        //불변식
        assert next != null;

        Money fee = next.calculateFee(calls);
        Money result = afterCalculated(fee);

        //사후 조건(마이너스 요금이 발생하더라도 X),사후조건을 완화 => 계약위반 => 리스코프치환원칙 위반
        //마이너스 금액을 반환하는 것보다 처리를 종료하는 것이 올바른 선택
        //assert result.isGreaterThanOrEqual(Money.ZERO);

        //사후조건2(강화) => 계약 위반 영향 X
        assert result.isGreaterThanOrEqual(Money.wons(100));

        //불변식
        assert next != null;

        return result;
    }

    abstract protected Money afterCalculated(Money fee);

}