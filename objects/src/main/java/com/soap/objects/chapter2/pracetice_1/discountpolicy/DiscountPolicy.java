package com.soap.objects.chapter2.pracetice_1.discountpolicy;

import com.soap.objects.chapter2.pracetice_1.Reservation;

public interface DiscountPolicy {
    Reservation calculate(Reservation reservation);
}