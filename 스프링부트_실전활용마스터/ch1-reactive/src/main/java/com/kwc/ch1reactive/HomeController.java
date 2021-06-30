package com.kwc.ch1reactive;

import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import reactor.core.publisher.Mono;

@Controller
public class HomeController {

    /**
     * 반환타입 Mono<String> : 템플릿의 이름을 나타내는 문자열을 리액티브 컨테이너인 Mono에 담아서 반환
     *
     * Mono는 0 또는 1개의 원소만 담을 수 있는 리액티브 발행자
     * 템플릿 이름만 반환할 때는 굳이 Mono에 담아서 반환할 필요 없다. 다음장에서 자세히
     */
    @GetMapping
    Mono<String> home() {
        return Mono.just("home");
    }

}
