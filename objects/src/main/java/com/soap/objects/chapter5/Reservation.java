package com.soap.objects.chapter5;

import com.soap.objects.chapter4.datacenter.Customer;
import com.soap.objects.chapter4.datacenter.Money;
import com.soap.objects.chapter4.datacenter.Screening;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
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
