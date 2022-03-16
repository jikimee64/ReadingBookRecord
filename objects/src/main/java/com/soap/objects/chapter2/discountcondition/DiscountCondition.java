package com.soap.objects.chapter2.discountcondition;

import com.soap.objects.chapter2.Reservation;

public interface DiscountCondition {
    boolean checkCondition(Reservation reservation);
}