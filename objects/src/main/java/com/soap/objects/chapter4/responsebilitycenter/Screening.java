package com.soap.objects.chapter4.responsebilitycenter;


import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

/**
 * 데이터 중심 설계시 생각하는 것
 * 1. 이 객체가 어떤 데이터를 포함해야 하는가?
 * 2. 이 객체가 데이터에 대해 수행해야하는 오퍼레이션이 무엇인가?
 */
@Getter
@Setter
public class Screening {

    private Movie movie;
    private int sequence;
    private LocalDateTime whenScreened;

    public Screening(Movie movie, int sequence, LocalDateTime whenScreened) {
        this.movie = movie;
        this.sequence = sequence;
        this.whenScreened = whenScreened;
    }

    public Money calculateFee(int audienceCount) {
        switch (movie.getMovieType()) {
            case AMOUNT_DISCOUNT:
                if (movie.isDiscountable(whenScreened, sequence)) {
                    return movie.calculateAmountDiscountedFee().times(audienceCount);
                }
                break;
            case PERCENT_DISCOUNT:
                if (movie.isDiscountable(whenScreened, sequence)) {
                    return movie.calculatePercentDiscountedFee().times(audienceCount);
                }
                break;
            case NONE_DISCOUNT:
                return movie.calculateNoneDiscountedFee().times(audienceCount);
        }
        return movie.calculateNoneDiscountedFee().times(audienceCount);

    }
}