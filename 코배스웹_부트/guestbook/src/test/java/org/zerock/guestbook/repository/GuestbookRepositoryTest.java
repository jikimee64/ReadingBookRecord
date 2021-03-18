package org.zerock.guestbook.repository;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;
import java.util.stream.IntStream;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.zerock.guestbook.dto.GuestBookDto;
import org.zerock.guestbook.dto.GuestBookSearchCondition;
import org.zerock.guestbook.entity.GuestBook;

@SpringBootTest
class GuestbookRepositoryTest {

    @Autowired
    private GuestbookRepository guestbookRepository;

    @Test
    @Disabled
    void insertDummies() {
        IntStream.rangeClosed(1, 300).forEach(i -> {
            GuestBook guestBook = GuestBook.builder()
                .title("Title..." + i)
                .content("Content..." + i)
                .writer("user" + (i % 10))
                .build();
            System.out.println(guestbookRepository.save(guestBook));
        });
    }

    @Test
    void updateTest() {
        Optional<GuestBook> result = guestbookRepository.findById(300L);

        result.ifPresent(book -> {
            book.changeTitle("Changed Title...");
            book.changeContent("Changed Content...");
            guestbookRepository.save(book);
        });
    }

    @Test
    @DisplayName("제목(title)에 '1'이라는 글자가 있는 엔티티 검색")
    public void testQuery1() {
        GuestBookSearchCondition condition = new GuestBookSearchCondition();
        condition.setTitle("1");
        condition.setContent("1");

        PageRequest pageRequest = PageRequest.of(0, 1, Sort.by("gno").descending());

        Page<GuestBookDto> result = guestbookRepository.searchPageAllAnd(condition, pageRequest);

        assertThat(result.getSize()).isEqualTo(1);
        assertThat(result.getContent()).extracting("title")
            .containsExactly("Title...1");
    }

    @Test
    @DisplayName("제목(title) 혹은 내용(content)에 특정 키워드가 있고 gno가 0보다 크다")
    public void testQuery2() {
        GuestBookSearchCondition condition = new GuestBookSearchCondition();
        condition.setTitle("1");
        condition.setContent("1");

        PageRequest pageRequest = PageRequest.of(0, 1, Sort.by("gno").descending());

        Page<GuestBookDto> result = guestbookRepository.searchPageTitleOrContentAndGnoGt(condition, pageRequest);

        assertThat(result.getSize()).isEqualTo(1);
        assertThat(result.getContent()).extracting("title")
            .containsExactly("Title...1");
    }

}