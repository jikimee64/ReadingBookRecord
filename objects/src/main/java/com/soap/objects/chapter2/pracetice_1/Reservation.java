package com.soap.objects.chapter2.pracetice_1;

import java.time.DayOfWeek;
import java.time.LocalTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Reservation {

    private Screening screening;
    private int count;
    private int totalPrice;
    private int totalDiscountPrice;

    public Reservation(Screening screening, int count) {
        this.screening = screening;
        this.count = count;
    }

    public void setTotalPrice(Movie movie){
        this.totalPrice = movie.setTotalPrice(count);
    }

    public boolean checkPeriodCondition(DayOfWeek dayOfWeek, LocalTime startPeriodTime,
        LocalTime endPeriodTime) {
        return screening.checkPeriodCondition(dayOfWeek, startPeriodTime, endPeriodTime);
    }

    public boolean checkSequenceCondition(int sequence) {
        return screening.checkSequenceCondition(sequence);
    }

    public Reservation discountAmount(int discountPrice) {
        this.totalDiscountPrice = totalPrice - (discountPrice * count);
        return this;
    }

    public Reservation discountPercent(int discountPercent) {
        this.totalDiscountPrice = totalPrice - (totalPrice / 10);
        return this;
    }

}