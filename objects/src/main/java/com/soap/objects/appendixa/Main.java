package com.soap.objects.appendixa;

import com.soap.objects.appendixa.AddPolicy.RateDiscountablePolicy;
import com.soap.objects.appendixa.AddPolicy.TaxablePolicy;
import com.soap.objects.appendixa.BasicPolicy.NightlyDiscountPolicy;
import com.soap.objects.appendixa.BasicPolicy.RegularPolicy;
import com.soap.objects.chapter10.bad.Call;
import com.soap.objects.chapter10.bad.Money;
import java.time.Duration;
import java.time.LocalDateTime;

public class Main {

    public static void main(String[] args) {

        //한번도 통화가 발생하지 않은 시나리오, call 메소드 호출 X
        Phone phone = new Phone(new RegularPolicy(Money.wons(100), Duration.ofSeconds(10)));
        Bill bill = phone.publishBill();


        //calculateFee 오퍼레이션의 반환값이 0보다 작은 경우우
        Phone hone2 = new Phone(
            new RateDiscountablePolicy(Money.wons(1000),
                new RegularPolicy(Money.wons(100), Duration.ofSeconds(10)))
        );
        phone.call(new Call(LocalDateTime.of(2017,1,1,10,10),
            LocalDateTime.of(2017,1,1,10,11)));

//        Phone phone1 = new Phone(
//            new NightlyDiscountPolicy(Money.wons(5), Money.wons(10), Duration.ofSeconds(10)));
//
//        //부가정책 추가
//
//        //일반 요금제 + 세금 정책
//        Phone phone2 = new Phone(
//            new TaxablePolicy(0.05, new RegularPolicy(Money.wons(10), Duration.ofSeconds(10))));
//        phone2.calculateFee();
//
//        //일반 요금제 + 기본 요금 할인 정책 + 세금 정책(순서 보장)
//        Phone phone3 = new Phone(
//            new TaxablePolicy(0.05,
//                new RateDiscountablePolicy(Money.wons(1000),
//                    new RegularPolicy(Money.wons(10), Duration.ofSeconds(10))
//                )
//            )
//        );
//        phone3.calculateFee();
//
//        //일반 요금제 + 세금 정책 + 일반 요금 할인 정책(순서 보장)
//        Phone phone4 = new Phone(
//            new RateDiscountablePolicy(Money.wons(1000),
//                new TaxablePolicy(0.05, new RegularPolicy(Money.wons(10), Duration.ofSeconds(10))
//                )));
//
//        //심야 요금제 + 세금 정책 + 일반 요금 할인 정책(순서 보장)
//        Phone phone5 = new Phone(
//            new RateDiscountablePolicy(Money.wons(1000),
//                new TaxablePolicy(0.05, new NightlyDiscountPolicy(Money.wons(5), Money.wons(10),
//                    Duration.ofSeconds(10)))
//            ));

    }

}