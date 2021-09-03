package com.soap.objects.chapter4;

import java.time.DayOfWeek;
import java.time.LocalTime;
import lombok.Getter;
import lombok.Setter;

/**
 * 데이터 중심의 설계
 *
 * sequence : 순번 조건에서만 사용하는 변수
 * dayOfWeek, startTime, endTime : 기간 조건에서만 사용되는 변수
 *
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
}