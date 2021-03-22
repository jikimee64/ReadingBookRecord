package org.zerock.guestbook.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

@Builder
@AllArgsConstructor
@Data
public class PageRequestDTO {

    private int page;
    private int size;
    private String type;
    private String keyword;

    public PageRequestDTO() {
        this.page = 1;
        this.size = 10;
    }

    /**
     * Pageable 타입의 객체를 생성
     */
    public Pageable getPageable(Sort sort){
        // page는 0부터 시작하므로 -1 감소(1페이지의 경우 -1을 하여 0부터 시작)
        return PageRequest.of(page -1, size, sort);
    }
}
