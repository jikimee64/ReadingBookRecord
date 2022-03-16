package com.soap.objects.chapter2.pracetice_1.discountpolicy;

import com.soap.objects.chapter2.pracetice_1.Reservation;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class PercentDiscountPolicy implements DiscountPolicy {

    int percent;

    /**
     * 예매 요금에서 일정 비율의 요금을 할인
     */
    @Override
    public Reservation calculate(Reservation reservation) {
        return reservation.discountPercent(percent);
    }

}