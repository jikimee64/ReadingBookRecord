package com.soap.objects.chapter2.pracetice_1;

import com.soap.objects.chapter2.pracetice_1.discountcondition.DiscountCondition;
import com.soap.objects.chapter2.pracetice_1.discountcondition.PeriodCondition;
import com.soap.objects.chapter2.pracetice_1.discountcondition.SequenceCondition;
import com.soap.objects.chapter2.pracetice_1.discountpolicy.AmountDiscountPolicy;
import com.soap.objects.chapter2.pracetice_1.discountpolicy.DiscountPolicy;
import com.soap.objects.chapter2.pracetice_1.discountpolicy.PercentDiscountPolicy;
import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.Arrays;

public class Client {

    public static void main(String[] args) {

        DiscountCondition discountSequenceCondition1 = new SequenceCondition(1);
        DiscountCondition discountSequenceCondition2 = new SequenceCondition(10);

        DiscountCondition discountPeridCondition1 = new PeriodCondition(
            DayOfWeek.MONDAY,
            LocalTime.of(10, 0, 0),
            LocalTime.of(12, 0, 0)
        );

        DiscountCondition discountPeridCondition2 = new PeriodCondition(
            DayOfWeek.THURSDAY,
            LocalTime.of(18, 0, 0),
            LocalTime.of(21, 0, 0)
        );

        DiscountPolicy discountPolicy1 = new AmountDiscountPolicy(800);
        DiscountPolicy discountPolicy2 = new PercentDiscountPolicy(10);

        Screening screening = new Screening(
            DayOfWeek.FRIDAY,1,LocalTime.of(10,0,0),
            LocalTime.of(12,0,0)
        );

        Movie movie = new Movie("아바타", 10_000, discountPolicy2,
            Arrays.asList(
                discountSequenceCondition1,
                discountSequenceCondition2,
                discountPeridCondition1,
                discountPeridCondition2
            )
        );
        MovieCenter movieCenter = new MovieCenter();
        Reservation reservation = movieCenter.enter(movie, screening);
        System.out.println(reservation);

    }
}