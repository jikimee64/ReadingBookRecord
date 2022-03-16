package com.soap.objects.chapter2.pracetice_1;

import com.soap.objects.chapter2.pracetice_1.discountcondition.DiscountCondition;
import com.soap.objects.chapter2.pracetice_1.discountpolicy.DiscountPolicy;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Movie {

    private String name;
    private Integer price;
    private DiscountPolicy discountPolicy;
    private List<DiscountCondition> discountConditions;

    public boolean checkDiscountCondition(Reservation reservation){
        return discountConditions.stream()
            .anyMatch(discountCondition -> discountCondition.checkCondition(reservation));
    }

    public Reservation checkDiscountPolicy(Reservation reservation){
        return discountPolicy.calculate(reservation);
    }

    public int setTotalPrice(int count){
        return price * count;
    }

}