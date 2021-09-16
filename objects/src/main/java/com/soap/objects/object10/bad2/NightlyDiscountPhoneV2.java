package com.soap.objects.object10.bad2;

import com.soap.objects.object10.bad.Call;
import com.soap.objects.object10.bad.Money;
import com.soap.objects.object10.bad.Phone;
import java.time.Duration;

public class NightlyDiscountPhoneV2 extends Phone {

    private static final int LATE_NIGHT_HOUR = 22;

    private Money nightlyAmount;

    public NightlyDiscountPhoneV2(Money nightlyAmount, Money regularAmount, Duration seconds) {
        super(regularAmount, seconds, 100);
        this.nightlyAmount = nightlyAmount;
    }

    @Override
    public Money calculateFee() {
        //부모 클래스의 calculateFee 호출
        Money result = super.calculateFee();

        Money nightlyFee = Money.ZERO;

        for (Call call : getCalls()) {
            if (call.getFrom().getHour() >= LATE_NIGHT_HOUR) {
                nightlyFee = nightlyFee.plus(
                    getAmount().minus(nightlyAmount.times(
                        call.getDuration().getSeconds() / getSeconds().getSeconds()
                    )));
            }
        }

        return result.minus(nightlyFee);
    }

}
