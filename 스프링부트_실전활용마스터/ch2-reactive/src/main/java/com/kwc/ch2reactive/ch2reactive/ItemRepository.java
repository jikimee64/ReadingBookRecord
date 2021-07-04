package com.kwc.ch2reactive.ch2reactive;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

// 반환타입이 Mono나 Flux 둘중 하나
// Mono나 Flux를 구독하고 있다가 몽고디비가 데이터를 제공할 준비가 됐을 때 데이터를 받을 수 있게 된다.
// 그리고 이 메소드 중 일부는 리액티브 스트림의 Publisher 타입을 인자로 받을 수 있다.
// ※ 모든 리액터 타입은 Publisher 리액티브 스트림 타입을 구현한다
public interface ItemRepository extends ReactiveCrudRepository<Item, String> {

}
