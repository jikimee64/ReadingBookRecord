package com.kwc.ch2reactive.ch2reactive;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Component;

/**
 * 블로킹 리포지토리를 사용하지 않고 블로킹 방식으로 데이터를 로딩하는 클래스
 */
@Component
public class TemplateDatabaseLoader {

    /**
     * 애플리케이션과 몽고디비의 결합도를 낮추기 위해 MongoOperations 사용
     *
     * MongoOperations : JdbcTemplate에서 일부를 추출해서 JdbcOperations라는 인터페이스를 만든것에서 파생
     * @param mongo
     * @return
     */
    @Bean
    CommandLineRunner initialize(MongoOperations mongo) {
        return args -> {
            mongo.save(new Item("Alf alarm clock", 19.99));
            mongo.save(new Item("Smurf TV tray", 24.99));
        };
    }

}
