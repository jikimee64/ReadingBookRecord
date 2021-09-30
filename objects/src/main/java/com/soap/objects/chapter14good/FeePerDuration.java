package com.soap.objects.chapter14good;


import java.time.Duration;

/**
 * 단위 시간당 요금
 */
public class FeePerDuration {
    private Money fee;
    private Duration duration;

    public FeePerDuration(Money fee, Duration duration) {
        this.fee = fee;
        this.duration = duration;
    }

    //일절 기간 동안의 요금을 계산
    public Money calculate(DateTimeInterval interval){
        return fee.times(interval.duration().getSeconds() / duration.getSeconds());
    }
}
