package com.kwc.ch2reactive.ch2reactive.example;

import java.util.Random;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Hooks;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

public class ReactorDebuggingExample {
    public static void main(String[] args) {

        Hooks.onOperatorDebug(); //리액터의 백트레이싱을 활성화
        //오류관련 핵심 정보를 스레드 경계를 넘어서 전달할 수 있는 방법을 만듬
        //이를 호출하면 리액터가 처리 흐름 조립 시점에서의 호출부 세부정보를 수집하고 구독해서 실행되는 시점에 세부정보를 넘김
        //스레드 경계를 넘어 전달하는 과정 자체가 자바에서는 성능이 많이 소비되기 때문에 운영환경에서는 절대 사용 금지!!!

        Mono<Integer> source;
        if (new Random().nextBoolean()) {
            source = Flux.range(1, 10).elementAt(5);
        } else {
            source = Flux.just(1, 2, 3, 4).elementAt(5); // line 89
        }
        source //
            .subscribeOn(Schedulers.parallel()) //
            .block(); // line 93
    }

}