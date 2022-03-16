package com.soap.objects.chapter2.pracetice_1.discountcondition;

import com.soap.objects.chapter2.pracetice_1.Reservation;

public interface DiscountCondition {
    boolean checkCondition(Reservation reservation);
}