package com.soap.objects.chapter2;

import com.soap.rabbit.chapter7.Customer;

/**

 * @author 2021.05.28
 * @version 1.0, 작업 내용
 */
public class Reservation {
    private Customer customer;
    private Screening screening;
    private Money fee;
    private int audienceCount;

    public Reservation(Customer customer, Screening screening, Money fee, int audienceCount) {
        this.customer = customer;
        this.screening = screening;
        this.fee = fee;
        this.audienceCount = audienceCount;
    }
}
