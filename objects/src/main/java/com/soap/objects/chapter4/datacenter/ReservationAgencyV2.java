package com.soap.objects.chapter4.datacenter;

import org.springframework.format.number.money.CurrencyUnitFormatter;

/**
 * 응집도 높은 메서드로 잘게 분리
 */
public class ReservationAgencyV2 {

    public Reservation reserve(Screening screening, Customer customer, int audienceCount) {

        boolean discountable = checkDiscountable(screening);
        Money fee = calculateFee(screening, discountable, audienceCount);
        return createReservation(screening, customer, audienceCount, fee);
    }


    private boolean checkDiscountable(Screening screening) {
        return screening.getMovie().getDiscountConditions().stream()
            .anyMatch(condition -> condition.isDiscountable(screening));
    }

    //1번 ==============================(위치 부적절)
//    private boolean isDiscountable(DiscountCondition condition, Screening screening) {
//        if (condition.getType() == DiscountConditionType.PERIOD) {
//            return isSatisfiedByPeriod(condition, screening);
//        }
//        return isSatisfiedBySequence(condition, screening);
//
//    }
//
//    private boolean isSatisfiedByPeriod(DiscountCondition condition, Screening screening) {
//        return screening.getWhenScreened().getDayOfWeek().equals(condition.getDayOfWeek()) &&
//            condition.getStartTime()
//                .compareTo(screening.getWhenScreened().toLocalTime()) <= 0 &&
//            condition.getEndTime().compareTo(screening.getWhenScreened().toLocalTime())
//                >= 0;
//    }
//
//    private boolean isSatisfiedBySequence(DiscountCondition condition, Screening screening) {
//        return condition.getSequence() == screening.getSequence();
//    }
    //==============================

    //2번 ==============================
    private Money calculateFee(Screening screening, boolean discountable, int audienceCount) {
        if (discountable) {
            return screening.getMovie().getFee()
                .minus(calculateDiscountedFee(screening.getMovie()))
                .times(audienceCount);
        }
        return screening.getMovie().getFee().times(audienceCount);
    }

    private Money calculateDiscountedFee(Movie movie) {
        switch (movie.getMovieType()) {
            case AMOUNT_DISCOUNT:
                return calculateAmountDiscountedFee(movie);
            case PERCENT_DISCOUNT:
                return calculatePercentDiscountedFee(movie);
            case NONE_DISCOUNT:
                return calculateNoneDiscountedFee(movie);
        }
        throw new IllegalArgumentException();
    }

    private Money calculateAmountDiscountedFee(Movie movie) {
        return movie.getDiscountAmount();
    }

    private Money calculatePercentDiscountedFee(Movie movie) {
        return movie.getFee().times(movie.getDiscountPercent());
    }

    private Money calculateNoneDiscountedFee(Movie movie) {
        return Money.ZERO;
    }
    //==============================

    private Reservation createReservation(Screening screening, Customer customer, int audienceCount,
        Money fee) {
        return new Reservation(customer, screening, fee, audienceCount);
    }

}
