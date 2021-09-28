package com.soap.objects.chapter14hard.AddPolicy;

import com.soap.objects.chapter10.bad.Money;
import com.soap.objects.chapter11.goodcomposition.Phone;
import com.soap.objects.chapter11.goodcomposition.RatePolicy;

//추상 클래스는 인터페이스의 메소드를 직접 구현 안해도됨 > 해당 추상클래스를 상속받는 자식클래스가 구현해야됨
public abstract class AdditionalRatePolicy implements RatePolicy {
    private RatePolicy next;

    public AdditionalRatePolicy(RatePolicy next) {
        this.next = next;
    }

    @Override
    public Money calculateFee(Phone phone){
        Money fee = next.calculateFee(phone);
        return afterCalculated(fee);
    }

    abstract protected Money afterCalculated(Money fee);

}