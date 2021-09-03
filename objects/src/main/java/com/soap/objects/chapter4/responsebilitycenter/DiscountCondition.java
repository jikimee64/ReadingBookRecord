package com.soap.objects.chapter4.responsebilitycenter;

import java.time.DayOfWeek;
import java.time.LocalTime;
import lombok.Getter;
import lombok.Setter;
import org.springframework.cglib.core.Local;

/**
 *  데이터 중심 설계시 생각하는 것
 *  1. 이 객체가 어떤 데이터를 포함해야 하는가?
 *  2. 이 객체가 데이터에 대해 수행해야하는 오퍼레이션이 무엇인가?
 *
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

    /**
     * 문제점 : type이 인스턴스로 포함되어있다는 것을 노출
     * @return
     */
    public DiscountConditionType getType() {
        return type;
    }

    /**
     * type의 값을 이용해 현재의 할인 조건 타입에 맞는 적절한 메서드가 호춫됬는지 판단
     * 문제점
     * 1. 파라미터들을 통해 dayOfWeek와 time이 인스턴스 변수로 포함되어있다는 것을 노출
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
     * 문제점
     *      * 1. 파라미터들을 통해 sequence이 인스턴스 변수로 포함되어있다는 것을 노출
     */
    public boolean isDiscountable(int sequence) {
        if (type != DiscountConditionType.SEQUENCE) {
            throw new IllegalArgumentException();
        }
        return this.sequence == sequence;
    }
}