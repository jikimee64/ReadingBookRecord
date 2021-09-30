package com.soap.objects.chapter14noconsistency;

import com.soap.objects.chapter10.bad.Money;
import java.util.ArrayList;
import java.util.List;

public class DurationDiscountPolicy extends BasicRatePolicy {

    private List<DurationDiscountRule> rules = new ArrayList<>();

    public DurationDiscountPolicy(
        List<DurationDiscountRule> rules) {
        this.rules = rules;
    }

    @Override
    protected Money calculateCallFee(Call call){
        Money result = Money.ZERO;
        for(DurationDiscountRule rule : rules){
            result.plus(rule.calculate(call));
        }
        return result;
    }

}