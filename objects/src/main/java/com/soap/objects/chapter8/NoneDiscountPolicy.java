package com.soap.objects.chapter8;

import com.soap.objects.chapter2.DiscountPolicy;
import com.soap.objects.chapter2.Money;
import com.soap.objects.chapter2.Screening;

public class NoneDiscountPolicy extends DiscountPolicy {

    //할인 정책이 존재하지 않는 다는 것도 할인 정책의 한 종류로 간주 (Movie 수정 X)
    @Override
    protected Money getDiscountAmount(Screening screening) {
        return Money.ZERO;
    }
}
