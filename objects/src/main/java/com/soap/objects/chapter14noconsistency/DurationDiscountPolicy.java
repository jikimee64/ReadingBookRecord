package com.soap.objects.chapter14noconsistency;

import com.soap.objects.chapter10.bad.Money;
import java.util.ArrayList;
import java.util.List;

public class DurationDiscountPolicy extends BasicRatePolicy {

    private List<DurationDiscouutRule> rules = new ArrayList<>();

    public DurationDiscountPolicy(
        List<DurationDiscouutRule> rules) {
        this.rules = rules;
    }

    @Override
    protected Money calculateCallFee(Call call){
        Money result = Money.ZERO;
        for(DurationDiscouutRule rule : rules){
            result.plus(rule.calculate(call));
        }
        return result;
    }

}