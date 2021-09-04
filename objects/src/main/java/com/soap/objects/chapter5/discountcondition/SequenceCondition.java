package com.soap.objects.chapter5.discountcondition;

import com.soap.objects.chapter5.Screening;

public class SequenceCondition implements DiscountCondition {
    private int sequence; //순번

    public SequenceCondition(int sequence) {
        this.sequence = sequence;
    }

    @Override
    public boolean isSatisfiedBy(Screening screening) {
        return sequence == screening.getSequence();
    }
}