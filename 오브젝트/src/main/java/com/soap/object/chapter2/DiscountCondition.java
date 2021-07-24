package com.soap.object.chapter2;

/**

 * @author 2021.05.31
 * @version 1.0, 작업 내용
 * @see None
 */
public interface DiscountCondition {
    boolean isSatisfiedBy(Screening screening);
}