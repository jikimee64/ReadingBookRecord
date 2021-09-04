package com.soap.objects.chapter5;

import com.soap.objects.chapter5.discountcondition.DiscountCondition;
import java.time.Duration;
import java.util.Arrays;
import java.util.List;

public abstract class Movie {

    private String title;
    private Duration runningTime;
    private Money fee;
    private List<DiscountCondition> discountConditions;

    public Movie(String title, Duration runningTime, Money fee,
        DiscountCondition... discountConditions) {
        this.title = title;
        this.runningTime = runningTime;
        this.fee = fee;
        this.discountConditions = Arrays.asList(discountConditions);
    }

    public Money calculateMovieFee(Screening screening) {
        if (isDiscountable(screening)) {
            return fee.minus(calculateDiscountAmount());
        }
        return fee;
    }

    private boolean isDiscountable(Screening screening) {
        return discountConditions.stream()
            //각 DiscountCondition에 할인 여부를 판단하라는 메시지 전송
            .anyMatch(condition -> condition.isSatisfiedBy(screening));
    }

    abstract protected Money calculateDiscountAmount();

    protected Money getFee() {
        return fee;
    }
}