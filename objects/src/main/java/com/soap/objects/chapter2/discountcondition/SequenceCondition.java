package com.soap.objects.chapter2.discountcondition;

import com.soap.objects.chapter2.Reservation;

public class SequenceCondition implements DiscountCondition {

    int sequence;

    public SequenceCondition(int sequence) {
        this.sequence = sequence;
    }

    @Override
    public boolean checkCondition(Reservation reservation) {
        return reservation.checkSequenceCondition(sequence);
    }

}