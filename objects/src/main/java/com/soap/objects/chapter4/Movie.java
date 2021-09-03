package com.soap.objects.chapter4;

import com.soap.objects.chapter4.DiscountCondition;
import com.soap.objects.chapter4.Money;
import java.time.Duration;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

/**
 * 데이터 중심의 설계
 *
 * 책임 중심설계와의 차이점
 * DiscountCondition(할인조건의 목록)이 인스턴스 변ㅅ부로 Movie안에 직접 포함
 * discountAmount(할인 금액)과 discountPercent(할인 비율)을 Movie안에 직접 포함
 *
 * MovieType : 할인 정책 종류 결정
 */
@Getter
@Setter
public class Movie {
    private String title;
    private Duration runningTime;
    private Money fee;
    private List<DiscountCondition> discountConditionList;

    private MovieType movieType;
    private Money discountAmount;
    private double discountPercent;

}
