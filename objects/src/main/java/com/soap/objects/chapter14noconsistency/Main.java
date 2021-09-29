package com.soap.objects.chapter14noconsistency;

public class Main {

    public static void main(String[] args) {
//        Phone phone = new Phone(new RegularPolicy(Money.wons(10), Duration.ofSeconds(10)));
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