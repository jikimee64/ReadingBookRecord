package com.soap.objects.chapter4.responsebilitycenter;

import java.time.DayOfWeek;
import java.time.LocalTime;
import lombok.Getter;
import lombok.Setter;
import org.springframework.cglib.core.Local;

/**
 * 데이터 중심의 설계
 * <p>
 * sequence : 순번 조건에서만 사용하는 변수 dayOfWeek, startTime, endTime : 기간 조건에서만 사용되는 변수
 * <p>
 * MovieType : 할인 정책 종류 결정
 */
@Getter
@Setter
public class DiscountCondition {

    private DiscountConditionType type;

    private int sequence;

    private DayOfWeek dayOfWeek;
    private LocalTime startTime;
    private LocalTime endTime;

    public DiscountConditionType getType() {
        return type;
    }

    /**
     * type의 값을 이용해 현재의 할인 조건 타입에 맞는 적절한 메서드가 호춫됬는지 판단
     */
    public boolean isDiscountable(DayOfWeek dayOfWeek, LocalTime time) {
        if (type != DiscountConditionType.PERIOD) {
            throw new IllegalArgumentException();
        }
        return this.dayOfWeek.equals(dayOfWeek) &&
            this.startTime.compareTo(time) <= 0 &&
            this.endTime.compareTo(time) >= 0;
    }

    /**
     * type의 값을 이용해 현재의 할인 조건 타입에 맞는 적절한 메서드가 호춫됬는지 판단
     */
    public boolean isDiscountable(int sequence) {
        if (type != DiscountConditionType.SEQUENCE) {
            throw new IllegalArgumentException();
        }
        return this.sequence == sequence;
    }
}