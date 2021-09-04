package com.soap.objects.chapter5;

import java.time.LocalDateTime;
import lombok.Getter;

@Getter
public class Screening {
    private Movie movie;
    private int sequence; //상영순번
    private LocalDateTime whenScreened; //상영시간


    //"예매하라"라는 메시지에 응답하는 메서드
    public Reservation reserve(Customer customer, int audienceCount){
        //return new Reservation(customer, this, calculateFee(audienceCount), audienceCount);
        return null;

    }

    //Movie에게 계산된 영화요금을 받은 뒤, 예매 인원수를 곱해서 최종 요금 리턴턴
    private Money calculateFee(int audienceCount){
        /**
         * calculateMovieFee : 송신자인 Screening의 의도를 표현
         * ★ Movie의 내부 구현에 대한 지식도 없이 전송할 메시지를 결정
         */
        return movie.calculateMovieFee(this).times(audienceCount);
    }

}