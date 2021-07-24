package com.kwc.ch2reactive.ch2reactive;

import org.springframework.data.repository.query.ReactiveQueryByExampleExecutor;

public interface ItemByExampleRepository extends ReactiveQueryByExampleExecutor<Item> {
    //ReactiveQueryByExampleExecutor로 가보면 Example 타입의 파라미터르 인자로 받아서
    // 검색을 수행하고 하나 또는 그 이상의 T 타입 값을 반환
    //정렬 옵션도 줄수 있고, 검색 결과 개수를 세거냐 데이터 존재 여부를 반환하는 메서드도 있음..
}
