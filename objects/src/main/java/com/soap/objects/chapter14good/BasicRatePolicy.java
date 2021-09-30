package com.soap.objects.chapter14good;

import com.soap.objects.appendixa.EmptyCallException;
import com.soap.objects.chapter14good.Money;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 기본 정책
 */
public class BasicRatePolicy implements RatePolicy{

    private List<FeeRule> feeRules = new ArrayList<>();

    public BasicRatePolicy(FeeRule... feeRules) {
        this.feeRules = Arrays.asList(feeRules);
    }

    //FeeRule 컬렉션을 이용해 전체 통화 요금을 계산
    @Override
    public Money calculateFee(Phone phone) throws EmptyCallException {
        return phone.getCalls()
            .stream()
            .map(call -> calculate(call))
            .reduce(Money.ZERO, (first, second) -> first.plus(second));
    }

    private Money calculate(Call call) {
        return feeRules
            .stream()
            .map(rule -> rule.calculateFee(call))
            .reduce(Money.ZERO, (first, second) -> first.plus(second));
    }
}
