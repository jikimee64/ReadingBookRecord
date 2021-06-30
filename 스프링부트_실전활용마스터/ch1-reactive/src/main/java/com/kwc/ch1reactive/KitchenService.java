package com.kwc.ch1reactive;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
public class KitchenService {

    private List<Dish> menu = Arrays.asList(
        new Dish("Sesame chicken"),
        new Dish("Lo mein noodles, plain"),
        new Dish("Sweet & sour beef")
    );

    private Random picker = new Random();

    /**
     * 요리 스트림 생성
     * 250ms 간격으로 요리를 계속 생성해서 return
     *
     * Flux.generate()는 Subscriber로부터 요청이 있을 때에 next 신호를 발생하는 Flux를 생성한다.
     * 즉 pull 방식의 Flux를 생성한다.
     * 단점 : 데이터 발생을 비동기나 push 방식으로 할 수 없다는 제약도 있다.
     * 해결 : Flux.create()를 사용하면 이런 제약 없이 비동기나 push 방식으로 데이터를 발생할 수 있다.
     */
    Flux<Dish> getDishes(){
        return Flux.<Dish>generate(sink -> sink.next(randomDish()))
            .delayElements(Duration.ofMillis(250)); //250ms 간격으로 요리 계속 제공
    }

    /**
     * 요리 무작위 선택
     */
    private Dish randomDish(){
        return menu.get(picker.nextInt(menu.size()));
    }

}
