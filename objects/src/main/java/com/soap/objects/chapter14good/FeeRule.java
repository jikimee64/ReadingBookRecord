package com.soap.objects.chapter14good;

import com.soap.objects.chapter14good.fee.FeeCondition;

/**
 * '규칙'을 구현하는 클래스
 */
public class FeeRule {
    private FeeCondition feeCondition; //단위 요금
    private FeePerDuration feePerDuration; //적용 조건 인터페이스

    public FeeRule(FeeCondition feeCondition,
        FeePerDuration feePerDuration) {
        this.feeCondition = feeCondition;
        this.feePerDuration = feePerDuration;
    }

    // 조건을 만족하는 시간의 목록을 반환받은 후 feePerDuration의 값을 이용해 요금을 계산
    public Money calculateFee(Call call) {
        return feeCondition.findTimeIntervals(call)
            .stream()
            .map(each -> feePerDuration.calculate(each))
            .reduce(Money.ZERO, (first, second) -> first.plus(second));
    }

}
