package com.kwc.ch2reactive.ch2reactive;


import org.springframework.data.repository.query.ReactiveQueryByExampleExecutor;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

// 반환타입이 Mono나 Flux 둘중 하나
// Mono나 Flux를 구독하고 있다가 몽고디비가 데이터를 제공할 준비가 됐을 때 데이터를 받을 수 있게 된다.
// 그리고 이 메소드 중 일부는 리액티브 스트림의 Publisher 타입을 인자로 받을 수 있다.
// ※ 모든 리액터 타입은 Publisher 리액티브 스트림 타입을 구현한다
@Repository
public interface ItemRepository extends ReactiveCrudRepository<Item, String>, ReactiveQueryByExampleExecutor<Item> {
    //고객이 입력한 검색어가 이름에 포함된 상품을 반환
//    Flux<Item> findByNameContaining(String partialName);
//    // end::code[]
//
//    // tag::code-2[]
////	@Query("{ 'name' : ?0, 'age' : ?1 }")
////	Flux<Item> findItemsForCustomerMonthlyReport(String name, int age);
////
////	@Query(sort = "{ 'age' : -1 }")
////	Flux<Item> findSortedStuffForWeeklyReport();
//    // end::code-2[]
//
//    // tag::code-3[]
//    // search by name
//    Flux<Item> findByNameContainingIgnoreCase(String partialName);
//
//    // search by description
//    Flux<Item> findByDescriptionContainingIgnoreCase(String partialName);
//
//    // search by name AND description
//    Flux<Item> findByNameContainingAndDescriptionContainingAllIgnoreCase(String partialName, String partialDesc);
//
//    // search by name OR description
//    Flux<Item> findByNameContainingOrDescriptionContainingAllIgnoreCase(String partialName, String partialDesc);
//    // end::code-3[]
}