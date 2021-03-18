package org.zerock.guestbook.dto;

import jdk.jfr.DataAmount;
import lombok.Data;

@Data
public class GuestBookDto {
    private String content;
    private String title;
    private String writer;

    public GuestBookDto(){
    }

    public GuestBookDto(String content, String title, String writer) {
        this.content = content;
        this.title = title;
        this.writer = writer;
    }
}
