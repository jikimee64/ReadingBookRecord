package com.kwc.ch2reactive.ch2reactive.example;

import java.util.Random;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

/* 이것 또한 마찬가지로 최초 문제 발생 지점인 Flux 생성 지점까지 출력하지 못한다.
*  이를 해결하기 위해 리액터의 Hooks.onOperatorDebug() 메서드
* */
public class ReactorExample {

    public static void main(String[] args) {
        Mono<Integer> source;
        // 랜덤하게 10개 또는 4개짜리 Flux를 생성하고 5번재 원소를 포함하는 Mono를 반환
        if(new Random().nextBoolean()){
            source = Flux.range(1, 10).elementAt(5);
        }else{
            source = Flux.just(1,2,3,4).elementAt(5);
        }

        source // 리액터 플로우가 여러 스레드에서 병렬 실행\
            .subscribeOn(Schedulers.parallel())
            .block();
    }

}
