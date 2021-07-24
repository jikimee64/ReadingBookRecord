package com.kwc.ch2reactive.ch2reactive;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * 비동기 코드에 블로킹 코드가 뒤섞이면 성능 X
 * 따라서 지우는게 맞음
 */
@Component
public class RepositoryDatabaseLoader {

    /**
     * CommandLineRunner : 애플리케이션이 시작된 후에 자동으로 실행되는 특수한 스프링 부트 컴포넌트
     * run() 메소드 하나만 갖고 있는 함수형 인터페이스
     * 애플리케이션에서 사용되는 모든 컴포넌트가 등록되고 활성화된 이후에 run() 메소드가 자동으로 실행되는
     * 것이 보장
     *
     * initialize() : BlockingItemRepository 메소드를 가지고 있다. 메소드가 실행될 때
     * 스프링 프레임워크에게 BlockingItemRepository 타입의 빈을 요청하며
     * 스프링은 등록된 빈 중에서 해당 타입의 빈을 찾아서 메소드 인자로 주입해준다.
     *
     * 람다식을 사용해서 CommandLineRunner의 구현체를 만듬
     *
     * ★ CommandLineRunner는 특정 순서로 동작하는 것이 보장 X
     * 따라서, 여러 개의 CommandLineRunner를 조율해서 어떤 순서에 따라 실행되도록 작성 X
     *
     * @param repository
     * @return
     */
//    @Bean
//    CommandLineRunner initialize(BlockingItemRepository repository) {
//        return args -> {
//            repository.save(new Item("Alf alarm clock", 19.99));
//            repository.save(new Item("Smurf TV tray", 24.99));
//        };
//    }

}
