package com.soap.objects.chapter2.pracetice_1.discountcondition;

import com.soap.objects.chapter2.pracetice_1.Reservation;

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