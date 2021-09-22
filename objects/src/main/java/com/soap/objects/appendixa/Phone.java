package com.soap.objects.appendixa;

import com.soap.objects.chapter10.bad.Call;
import com.soap.objects.chapter10.bad.Money;
import java.util.ArrayList;
import java.util.List;

public class Phone {

    private RatePolicy ratePolicy; //★★★★★ (합성)
    private List<Call> calls = new ArrayList();

    public Phone(RatePolicy ratePolicy) {
        this.ratePolicy = ratePolicy;
    }

    public List<Call> getCalls() {
        return calls;
    }

    public void call(Call call){
        calls.add(call);
    }

    public Bill publishBill(){
        return new Bill(this, ratePolicy.calculateFee(calls));
    }
}