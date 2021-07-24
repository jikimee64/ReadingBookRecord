package com.soap.object.chapter2;

/**

 * @author 2021.06.02
 * @version 1.0, 작업 내용
 * @see None
 */
public class PercentDiscountPolicy extends DiscountPolicy {
    private double percent; // 할인 비율 저장

    public PercentDiscountPolicy(double percent, DiscountCondition ... conditions) {
        super(conditions);
        this.percent = percent;
    }

    @Override
    protected Money getDiscountAmount(Screening screening) {
        return screening.getMovieFee().times(percent);
    }
}