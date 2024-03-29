package com.soap.objects.chapter2;

/**

 * @author 2021.05.31
 * @version 1.0, 작업 내용
 */
public class SequenceCondition implements DiscountCondition {
    private int sequence;

    public SequenceCondition(int sequence) {
        this.sequence = sequence;
    }

    public boolean isSatisfiedBy(Screening screening){
        return screening.isSequence(sequence);
    }
}