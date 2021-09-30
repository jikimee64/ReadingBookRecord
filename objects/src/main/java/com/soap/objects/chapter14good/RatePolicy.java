package com.soap.objects.chapter14good;

import com.soap.objects.appendixa.EmptyCallException;
import com.soap.objects.chapter14good.Money;
import com.soap.objects.chapter14noconsistency.Call;
import java.util.List;

//기본정책과 부가정책을 포괄
public interface RatePolicy {
    Money calculateFee(Phone phone) throws EmptyCallException;
}