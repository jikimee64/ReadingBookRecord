package com.soap.objects.appendixa;

import com.soap.objects.chapter10.bad.Call;
import com.soap.objects.chapter10.bad.Money;
import java.util.List;

//기본정책과 부가정책을 포괄
public interface RatePolicy {
    Money calculateFee(List<Call> calls) throws EmptyCallException;
}