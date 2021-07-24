package com.kwc.ch2reactive.ch2reactive;

import org.springframework.data.repository.CrudRepository;

/**
 * 비동기 코드에 블로킹 코드가 뒤섞이면 성능 X
 * 따라서 지우는게 맞음
 */
//public interface BlockingItemRepository extends CrudRepository<Item, String> {
//
//}
