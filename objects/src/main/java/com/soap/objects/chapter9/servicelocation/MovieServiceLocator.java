package com.soap.objects.chapter9.servicelocation;

import com.soap.objects.chapter2.DiscountPolicy;
import com.soap.objects.chapter2.Money;
import java.time.Duration;

/**

 * @author 2021.05.31
 * @version 1.0, 작업 내용
 */
public class MovieServiceLocator {
    private String title;
    private Duration runningTime;
    private Money fee;
    private DiscountPolicy discountPolicy;

    public MovieServiceLocator(String title, Duration runningTime, Money fee) {
        this.title = title;
        this.runningTime = runningTime;
        this.fee = fee;
        this.discountPolicy = ServiceLocator.discountPolicy();
    }

}
