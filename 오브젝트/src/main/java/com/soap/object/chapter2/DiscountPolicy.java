package com.soap.object.chapter2;

/**

 * @author 2021.05.31
 * @version 1.0, 작업 내용
 * @see None
 */
public abstract class DiscountPolicy {
    private List<DiscountCondition> conditions = new ArrayList();

    public DiscountPolicy(DiscountCondition ... conditions) {
        this.conditions = Arrays.asList(conditions);
    }

    public Money calculateDiscountAmount(Screening screening){
        for(DiscountCondition each : conditions){
            if(each.isSatisfiedBy(screening)){
                return getDiscountAmount(screening);
            }
        }
        return Money.ZERO;
    }

    abstract protected Money getDiscountAmount(Screening screening);
}