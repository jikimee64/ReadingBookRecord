package com.kwc.ch2reactive.ch2reactive;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;
import org.springframework.data.mongodb.core.ReactiveFluentMongoOperations;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import static org.springframework.data.mongodb.core.query.Criteria.byExample;
import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

@Service
@RequiredArgsConstructor
public class InventoryService {

    private ItemRepository repository;
    private ReactiveFluentMongoOperations fluentOperations;

    // 이직을 부르는 코드...
    Flux<Item> search(String partialName, String partialDescription, boolean useAnd) {
        if (partialName != null) {
            if (partialDescription != null) {
                if (useAnd) {
                    return repository //
                        .findByNameContainingAndDescriptionContainingAllIgnoreCase( //
                            partialName, partialDescription);
                } else {
                    return repository.findByNameContainingOrDescriptionContainingAllIgnoreCase( //
                        partialName, partialDescription);
                }
            } else {
                return repository.findByNameContaining(partialName);
            }
        } else {
            if (partialDescription != null) {
                return repository.findByDescriptionContainingIgnoreCase(partialDescription);
            } else {
                return repository.findAll();
            }
        }
    }

    Flux<Item> searchByExample(String name, String description, boolean useAnd){
        // 검색어를 입력받아서 새 Item 객체를 생성, price값은 null일 수 없으므로 0.0을 입력
        Item item = new Item(name, description, 0.0);

        ExampleMatcher matcher = (useAnd //사용자가 입력한 useAnd 값에 따라 3항 연산자로 분기해서 ExampleMatcher 생성
            ? ExampleMatcher.matchingAll()
            : ExampleMatcher.matchingAny())
            .withStringMatcher(StringMatcher.CONTAINING) //부분일치검색 수행
            .withIgnoreCase() //대소문자 구분 X
            .withIgnorePaths("price"); //ExampleMatcher는 기본적으로 null 필드를 무시하지만, 기본 타입인
        //double에는 null이 올 수 없으므로 price 필드가 무시되도록 명시적으로 지정한다.

        //Item 객체와 matcher를 함께 Example.of(...)로 감싸서 Example을 생성
        Example<Item> probe = Example.of(item, matcher);

        return repository.findAll();
    }

    //평문형 연산 : 몽고디비 쿼리를 보통 문장 같은 형식으로 사용
    // 몽고 디비에서 { $and: [ { name: 'TV tray' }, { description: ' Smurf' } ] } 와 쿼리가 같음
    Flux<Item> searchByFluentExample(String name, String description){
        return fluentOperations.query(Item.class)
            .matching(query(where("TV tray").is(name).and("Smurf").is(description)))
            .all();
    }

}
