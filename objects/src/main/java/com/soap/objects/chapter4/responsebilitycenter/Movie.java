package com.soap.objects.chapter4.responsebilitycenter;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

/**
 * 데이터 중심 설계시 생각하는 것
 * 1. 이 객체가 어떤 데이터를 포함해야 하는가?
 * 2. 이 객체가 데이터에 대해 수행해야하는 오퍼레이션이 무엇인가?
 *
 * 영화 요금을 계산, 할인 여부를 판단하는 오퍼레이션 필요
 */
@Getter
@Setter
public class Movie {

    private String title;
    private Duration runningTime;
    private Money fee;
    private List<DiscountCondition> discountConditions;

    private MovieType movieType;
    private Money discountAmount;
    private double discountPercent;

    //메서드 이름 자체로인해 할인정책을 노출
    public Money calculateAmountDiscountedFee() {
        if (movieType != MovieType.AMOUNT_DISCOUNT) {
            throw new IllegalArgumentException();
        }
        return fee.minus(discountAmount);
    }

    //메서드 이름 자체로인해 할인정책을 노출
    public Money calculatePercentDiscountedFee() {
        if (movieType != MovieType.PERCENT_DISCOUNT) {
            throw new IllegalArgumentException();
        }
        return fee.minus(fee.times(discountPercent));
    }

    //메서드 이름 자체로인해 할인정책을 노출
    public Money calculateNoneDiscountedFee() {
        if (movieType != MovieType.NONE_DISCOUNT) {
            throw new IllegalArgumentException();
        }
        return fee;
    }

    /**
     * DiscountContion과 결합도가 높은 상태
     * 1. 기간 할인 조건의 명칭(PERIOD)이 바뀐다면 Movie를 수정
     * 2. 할인 종류가 추가되면 if-elseif-else문 수정
     * 3. DiscountConditon의 만족여부를 판단하는데 필요한 정보가 바뀌면 isDiscountable() 메소드로 전달되는 파라미터를 수정
     * => 이 메서드에 의존하는 Screening 객체도 변경가능성 높음
     */
    public boolean isDiscountable(LocalDateTime whenScreened, int sequence) {
        for (DiscountCondition condition : discountConditions) {
            if (condition.getType() == DiscountConditionType.PERIOD) {
                if (condition.isDiscountable(whenScreened.getDayOfWeek(),
                    whenScreened.toLocalTime())) {
                    return true;
                }
            } else {
                if (condition.isDiscountable(sequence)) {
                    return true;
                }
            }
        }
        return false;
    }

}
