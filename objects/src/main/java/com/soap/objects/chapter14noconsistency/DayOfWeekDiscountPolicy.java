package com.soap.objects.chapter14noconsistency;

import com.soap.objects.chapter10.bad.Money;
import java.util.ArrayList;
import java.util.List;

/**
 * 통화 기간이 여러 날에 걸쳐있을 수 있음
 */
public class DayOfWeekDiscountPolicy extends BasicRatePolicy {

    private List<DayOfWeekDiscountRule> rules = new ArrayList<>();

    public DayOfWeekDiscountPolicy(
        List<DayOfWeekDiscountRule> rules) {
        this.rules = rules;
    }

    @Override
    protected Money calculateCallFee(Call call) {
        Money result = Money.ZERO;
        for(DateTimeInterval interval: call.getInterval().splitByDay()){
            for(DayOfWeekDiscountRule rule : rules){
                result.plus(rule.calculate(interval));
            }
        }
        return result;
    }
}
