package com.soap.objects.chapter11.goodcomposition;

import com.soap.objects.chapter10.bad.Money;

//기본정책과 부가정책을 포괄
public interface RatePolicy {
    Money calculateFee(Phone phone);
}