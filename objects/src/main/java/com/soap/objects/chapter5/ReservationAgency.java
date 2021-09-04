package com.soap.objects.chapter5;

import com.soap.objects.chapter4.datacenter.Customer;
import com.soap.objects.chapter4.datacenter.DiscountCondition;
import com.soap.objects.chapter4.datacenter.DiscountConditionType;
import com.soap.objects.chapter4.datacenter.Money;
import com.soap.objects.chapter4.datacenter.Movie;
import com.soap.objects.chapter4.datacenter.Reservation;
import com.soap.objects.chapter4.datacenter.Screening;

public class ReservationAgency {

    public Reservation reserve(Screening screening, Customer customer, int audienceCount) {
        Movie movie = screening.getMovie();

        boolean discountable = false;
        for (DiscountCondition condition : movie.getDiscountConditions()) {
            if (condition.getType() == DiscountConditionType.PERIOD) {
                discountable =
                    screening.getWhenScreened().getDayOfWeek().equals(condition.getDayOfWeek()) &&
                        condition.getStartTime()
                            .compareTo(screening.getWhenScreened().toLocalTime()) <= 0 &&
                        condition.getEndTime().compareTo(screening.getWhenScreened().toLocalTime())
                            >= 0;
            } else {
                discountable = condition.getSequence() == screening.getSequence();
            }

            if (discountable) {
                break;
            }
        }

        com.soap.objects.chapter4.datacenter.Money fee;
        if (discountable) {
            Money discountAmount = com.soap.objects.chapter4.datacenter.Money.ZERO;
            switch (movie.getMovieType()) {
                case AMOUNT_DISCOUNT:
                    discountAmount = movie.getDiscountAmount();
                    break;
                case PERCENT_DISCOUNT:
                    discountAmount = movie.getFee().times(movie.getDiscountPercent());
                    break;
                case NONE_DISCOUNT:
                    discountAmount = Money.ZERO;
                    break;
            }

            fee = movie.getFee().minus(discountAmount);
        } else {
            fee = movie.getFee();
        }
        return new Reservation(customer, screening, fee, audienceCount);
    }

}
