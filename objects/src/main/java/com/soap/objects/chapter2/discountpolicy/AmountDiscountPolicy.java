package com.soap.objects.chapter2.discountpolicy;

import com.soap.objects.chapter2.Reservation;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class AmountDiscountPolicy implements DiscountPolicy {

    int discountPrice;

    /**
     * 예매 요금에서 일정 금액을 할인
     */
    @Override
    public Reservation calculate(Reservation reservation) {
        return reservation.discountAmount(discountPrice);
    }

}