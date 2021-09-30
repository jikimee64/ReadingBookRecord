package com.soap.objects.chapter14good.policy;

import com.soap.objects.chapter14good.Call;
import com.soap.objects.chapter14good.DateTimeInterval;
import com.soap.objects.chapter14good.fee.FeeCondition;
import java.util.Arrays;
import java.util.List;

public class FixedFeeCondition implements FeeCondition {
    @Override
    public List<DateTimeInterval> findTimeIntervals(Call call) {
        return Arrays.asList(call.getInterval());
    }
}