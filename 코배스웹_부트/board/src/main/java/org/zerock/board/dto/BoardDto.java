package org.zerock.board.dto;

import java.time.LocalDateTime;
import lombok.Data;

@Data
public class BoardDto {
    private Long id;
    private LocalDateTime regDate;
    private LocalDateTime modDate;
    private String title;
    private String content;

    private String name;
    private String email;

    private Long countReply;

    // 목록 페이지
    public BoardDto(Long id, LocalDateTime regDate, String title,
        String name, String email, Long countReply) {
        this.id = id;
        this.regDate = regDate;
        this.title = title;
        this.name = name;
        this.email = email;
        this.countReply = countReply;
    }

    // 상세 페이지
    public BoardDto(Long id, LocalDateTime regDate, LocalDateTime modDate ,
        String title, String content, String name, String email, Long countReply) {
        this.id = id;
        this.regDate = regDate;
        this.modDate = modDate;
        this.title = title;
        this.content = content;
        this.name = name;
        this.email = email;
        this.countReply = countReply;
    }
}
