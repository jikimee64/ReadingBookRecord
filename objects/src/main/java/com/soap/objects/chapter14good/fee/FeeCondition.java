package com.soap.objects.chapter14good.fee;

import com.soap.objects.chapter14good.Call;
import com.soap.objects.chapter14good.DateTimeInterval;
import java.util.List;

public interface FeeCondition {
    List<DateTimeInterval> findTimeIntervals(Call call);


}
