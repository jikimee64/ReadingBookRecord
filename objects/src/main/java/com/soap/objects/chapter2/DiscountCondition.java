package com.soap.objects.chapter2;

/**

 * @author 2021.05.31
 * @version 1.0, 작업 내용
 */
public interface DiscountCondition {
    boolean isSatisfiedBy(Screening screening);
}