package com.soap.objects.chapter14noconsistency;

import com.soap.objects.chapter10.bad.Money;
import com.soap.objects.chapter14noconsistency.BasicPolicy.BasicRatePolicy;
import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

/**
 * 통화 기간이 여러 날에 걸쳐있을 수 있음
 */
public class TimeOfDayDiscountPolicy extends BasicRatePolicy {

    private List<LocalTime> starts = new ArrayList();
    private List<LocalTime> ends = new ArrayList<>();
    private List<Duration> durations = new ArrayList<>();
    private List<Money> amounts = new ArrayList<>();

    @Override
    protected Money calculateCallFee(Call call) {
        Money result = Money.ZERO;
        for (DateTimeInterval interval : call.splitByDay()) {
            for (int loop = 0; loop < starts.size(); loop++) {
                result.plus(amounts.get(loop).times(
                    Duration.between(from(interval, starts.get(loop)), to(interval, ends.get(loop)))
                        .getSeconds() / durations.get(loop).getSeconds()));
            }
        }
        return result;
    }

    private LocalTime from(DateTimeInterval interval, LocalTime from) {
        return interval.getFrom().toLocalTime().isBefore(from) ? from : interval.getFrom().toLocalTime();
    }

    private LocalTime to(DateTimeInterval interval, LocalTime to){
        return interval.getTo().toLocalTime().isAfter(to) ? to : interval.getTo().toLocalTime();
    }

}