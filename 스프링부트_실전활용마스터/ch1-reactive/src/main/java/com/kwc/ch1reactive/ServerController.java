package com.kwc.ch1reactive;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
public class ServerController {

    private final KitchenService kitchen;

    public ServerController(KitchenService kitchen) {
        this.kitchen = kitchen;
    }

    /**
     * 컨트롤러 메소드에서 리액터 타입을 반환하도록 작성하면 스프링 웹플럭스가 적절한 옵션과 함께
     * 적절한 타이밍에 구독
     */
    @GetMapping(value = "/server", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    Flux<Dish> serveDishes(){
        return this.kitchen.getDishes();
    }

    @GetMapping(value = "/served-dishes", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    Flux<Dish> deliverDishes(){
        return this.kitchen.getDishes()
            .map(dish -> Dish.deliver(dish));
    }
}
