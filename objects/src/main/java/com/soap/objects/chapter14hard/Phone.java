package com.soap.objects.chapter14hard;

import com.soap.objects.chapter10.bad.Money;
import java.util.ArrayList;
import java.util.List;

public class Phone {

    private RatePolicy ratePolicy; //★★★★★ (합성)
    private List<Call> calls = new ArrayList();

    public Phone(RatePolicy ratePolicy){
        this.ratePolicy = ratePolicy;
    }

    public List<Call> getCalls() {
        return calls;
    }

    public Money calculateFee(){
        return ratePolicy.calculateFee(this);
    }

}