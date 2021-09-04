package com.soap.objects.chapter5.discountcondition;

import com.soap.objects.chapter4.responsebilitycenter.DiscountConditionType;
import com.soap.objects.chapter5.Screening;
import java.time.DayOfWeek;
import java.time.LocalTime;

public interface DiscountCondition {
    boolean isSatisfiedBy(Screening screening);
}