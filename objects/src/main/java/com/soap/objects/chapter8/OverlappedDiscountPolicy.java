package com.soap.objects.chapter8;

import com.soap.objects.chapter2.DiscountCondition;
import com.soap.objects.chapter2.DiscountPolicy;
import com.soap.objects.chapter2.Money;
import com.soap.objects.chapter2.Screening;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class OverlappedDiscountPolicy extends DiscountPolicy {

    private List<DiscountPolicy> discountPolicies = new ArrayList<>();

    public OverlappedDiscountPolicy(DiscountPolicy... discountPolicies) {
        this.discountPolicies = Arrays.asList(discountPolicies);
    }

    //중복 할인도 할인정책의 한 종류로 간주 (Movie 수정 X)
    @Override
    protected Money getDiscountAmount(Screening screening) {
        Money result = Money.ZERO;
        for(DiscountPolicy each : discountPolicies){
            result = result.plus(each.calculateDiscountAmount(screening));
        }
        return result;
    }
}