package com.soap.object.chapter2;

/**

 * @author 2021.06.02
 * @version 1.0, 작업 내용
 * @see None
 */
public class AmountDiscountPolicy extends DiscountPolicy {
    private Money discountAmount; //할인 요금 저장

    public AmountDiscountPolicy(Money discountAmount, DiscountCondition ... conditions) {
        super(conditions);
        this.discountAmount = discountAmount;
    }

    @Override
    protected Money getDiscountAmount(Screening screening) {
        return discountAmount;
    }
}