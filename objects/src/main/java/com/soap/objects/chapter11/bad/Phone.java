package com.soap.objects.chapter11.bad;

import com.soap.objects.chapter10.bad.Call;
import com.soap.objects.chapter10.bad.Money;
import java.util.ArrayList;
import java.util.List;

public abstract class Phone {

//    private double taxRate;
    private List<Call> calls = new ArrayList();

    //훅 메소드
    protected Money afterCalculated(Money fee) {
        return fee;
    }

    public Money calculateFee(){
        Money result = Money.ZERO;

        for(Call call : calls){
            result = result.plus(calculateCallFee(call));
        }

        return afterCalculated(result);

    }

    public List<Call> getCalls() {
        return calls;
    }


    abstract protected Money calculateCallFee(Call call);
//    abstract protected Money afterCalculated(Money money);

}