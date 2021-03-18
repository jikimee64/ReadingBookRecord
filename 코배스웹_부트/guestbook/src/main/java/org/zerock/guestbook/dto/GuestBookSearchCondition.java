package org.zerock.guestbook.dto;

import lombok.Data;

@Data
public class GuestBookSearchCondition {
    //내용, 제목, 작성자
    private String content;
    private String title;
    private String writer;
}
