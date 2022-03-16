package com.soap.objects.chapter2.discountpolicy;

import com.soap.objects.chapter2.Reservation;

public interface DiscountPolicy {
    Reservation calculate(Reservation reservation);
}