package com.soap.objects.chapter14good.policy;

import com.soap.objects.chapter14good.Call;
import com.soap.objects.chapter14good.DateTimeInterval;
import com.soap.objects.chapter14good.fee.FeeCondition;
import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class DayOfWeekFeeCondition implements FeeCondition {
    private List<DayOfWeek> dayOfWeeks = new ArrayList();

    public DayOfWeekFeeCondition(DayOfWeek... dayOfWeeks) {
        this.dayOfWeeks = Arrays.asList(dayOfWeeks);
    }

    /**
     * Call의 기간 중에서 요일에 해당하는 기간만을 추출해 반환
     */
    @Override
    public List<DateTimeInterval> findTimeIntervals(Call call) {
        return call.getInterval()
            .splitByDay()
            .stream()
            .filter(each -> dayOfWeeks.contains(each.getFrom().getDayOfWeek()))
            .collect(Collectors.toList());
    }
}
