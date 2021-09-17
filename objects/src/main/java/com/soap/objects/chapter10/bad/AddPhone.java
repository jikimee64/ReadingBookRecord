package com.soap.objects.chapter10.bad;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class AddPhone {
    private static final int LATE_NIGHT_HOUR = 22;
    enum PhoneType { REGULAR, NIGHTLY }

    private PhoneType type;

    private Money amount;
    private Money regularAMount;
    private Money nightlyAmount;
    private Duration seconds;
    private List<Call> calls = new ArrayList<>();

    public AddPhone(Money amount, Duration seconds){
        this(PhoneType.REGULAR, amount, Money.ZERO, Money.ZERO, seconds);
    }

    public AddPhone(Money nightlyAmount, Money regularAmount, Duration seconds){
        this(PhoneType.NIGHTLY, Money.ZERO, nightlyAmount, regularAmount, seconds);
    }

    public AddPhone(PhoneType type, Money amount, Money regularAMount,
        Money nightlyAmount, Duration seconds) {
        this.type = type;
        this.amount = amount;
        this.regularAMount = regularAMount;
        this.nightlyAmount = nightlyAmount;
        this.seconds = seconds;
    }

    public Money calculateFee(){
        Money result = Money.ZERO;

        for(Call call : calls) {
            if(type == PhoneType.REGULAR){
                result = result.plus(
                    amount.times(call.getDuration().getSeconds() / seconds.getSeconds())
                );
            }else{
                if(call.getFrom().getHour() >= LATE_NIGHT_HOUR){
                    result = result.plus(
                        nightlyAmount.times(call.getDuration().getSeconds() / seconds.getSeconds())
                    );
                }else{
                    result = result.plus(
                        regularAMount.times(call.getDuration().getSeconds() / seconds.getSeconds())
                    );
                }
            }
        }
        return result;
    }
}
