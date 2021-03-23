package org.zerock.guestbook.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;

import java.time.LocalDateTime;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.zerock.guestbook.dto.GuestBookDTO;
import org.zerock.guestbook.dto.GuestBookDTO.Request.Modify;
import org.zerock.guestbook.dto.GuestBookDTO.Request.Save;
import org.zerock.guestbook.dto.GuestBookDTO.Response.Read;
import org.zerock.guestbook.dto.PageRequestDTO;
import org.zerock.guestbook.dto.PageResultDTO;
import org.zerock.guestbook.entity.GuestBook;

@SpringBootTest
class GuestBookServiceTest {

    @Autowired
    private GuestBookService service;

    @Test
    @DisplayName("PageResultDTO 수정전")
    void testList_v1() {
        PageRequestDTO pageRequestDTO = PageRequestDTO.builder()
            .page(1)
            .size(10)
            .build();

        PageResultDTO<GuestBookDTO.Response.Read, GuestBook> resultDTO = service
            .getList(pageRequestDTO);

        for (GuestBookDTO.Response.Read guestBookDTO : resultDTO.getDtoList()) {
            System.out.println(guestBookDTO);
        }
    }

    @Test
    @DisplayName("PageResultDTO 수정후")
    void testList_v2() {
        PageRequestDTO pageRequestDTO = PageRequestDTO.builder()
            .page(1)
            .size(10)
            .build();

        PageResultDTO<GuestBookDTO.Response.Read, GuestBook> resultDTO = service
            .getList(pageRequestDTO);
        System.out.println("PREV: " + resultDTO.isPrev()); // 1페이지 이므로 false
        System.out.println("NEXT: " + resultDTO.isNext()); // 다음 페이지로 가능 링크 필요하므로 true
        System.out.println("TOTAL: " + resultDTO.getTotalPage()); // 전체 페이지 개수

        System.out.println("---------------------------------");
        for (GuestBookDTO.Response.Read guestBookDTO : resultDTO.getDtoList()) {
            System.out.println(guestBookDTO);
        }

        System.out.println("---------------------------------");
        resultDTO.getPageList().forEach(i -> System.out.println(i));

    }

    @Test
    @DisplayName("게시글 등록")
    void save() {

        Save dto = this.saveDto();

        Long savedGno = service.register(this.toEntity(dto));

        assertNotNull(savedGno);
    }

    @Test
    @DisplayName("게시글 수정")
    void modify() {

        Save dto = this.saveDto();

        Long savedGno = service.register(this.toEntity(dto));

        Modify modifyDto = Modify.builder()
            .gno(savedGno)
            .title("게시글_제목수정")
            .content("게시글_내용수정")
            .build();

        service.modify(modifyDto);
        Read read = service.read(savedGno);

        assertThat(read.getTitle()).isEqualTo(modifyDto.getTitle());
        assertThat(read.getContent()).isEqualTo(modifyDto.getContent());
    }

    @Test
    @DisplayName("게시글 삭제")
    void delete() {

        Save dto = this.saveDto();

        Long savedGno = service.register(this.toEntity(dto));

        service.remove(savedGno);

        assertNull(service.read(savedGno));
    }


    @Test
    @DisplayName("게시글 리스트 검색")
    void search() {

        PageRequestDTO pageRequestDTO = PageRequestDTO.builder()
            .page(1)
            .size(10)
            .type("tc")
            .keyword("123")
            .build();

        PageResultDTO<Read, GuestBook> resultDTO = service.getList(pageRequestDTO);

        assertThat(resultDTO.isPrev()).isEqualTo(false);
        assertThat(resultDTO.isNext()).isEqualTo(false);
        assertThat(resultDTO.getTotalPage()).isEqualTo(1);
        assertThat(resultDTO.getDtoList().size()).isEqualTo(1);
    }

    private GuestBookDTO.Request.Save saveDto() {
        return Save.builder()
            .title("게시글_제목")
            .content("게시글_내용")
            .writer("게시글_작성자")
            .regDate(LocalDateTime.now())
            .modDate(LocalDateTime.now())
            .build();
    }

    private GuestBook toEntity(GuestBookDTO.Request.Save dto) {
        return GuestBook.builder()
            .title(dto.getTitle())
            .content(dto.getContent())
            .writer(dto.getWriter())
            .build();
    }


}