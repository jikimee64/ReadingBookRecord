package org.zerock.guestbook.dto;

import java.time.LocalDateTime;
import jdk.jfr.DataAmount;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.zerock.guestbook.entity.GuestBook;

public class GuestBookDTO {
    public static class Request {

        @Data
        @NoArgsConstructor
        public static class Save{
            private String title;
            private String content;
            private String writer;
            private LocalDateTime regDate, modDate;

            @Builder
            public Save(String title, String content, String writer, LocalDateTime regDate,
                LocalDateTime modDate) {
                this.title = title;
                this.content = content;
                this.writer = writer;
                this.regDate = regDate;
                this.modDate = modDate;
            }

            public static GuestBook toEntity(GuestBookDTO.Request.Save dto){
                return GuestBook.builder()
                    .title(dto.getTitle())
                    .content(dto.getContent())
                    .writer(dto.getWriter())
                    .build();
            }
        } //Save

        @Data
        @NoArgsConstructor
        public static class Modify{
            private Long gno;
            private String title;
            private String content;

            @Builder
            public Modify(Long gno, String title, String content) {
                this.gno = gno;
                this.title = title;
                this.content = content;
            }
        } //Modify
    }

    public static class Response {

        @Data
        @NoArgsConstructor
        public static class Read{
            private Long gno;
            private String title;
            private String content;
            private String writer;
            private LocalDateTime regDate, modDate;

            @Builder
            public Read(Long gno, String title, String content, String writer,
                LocalDateTime regDate,
                LocalDateTime modDate) {
                this.gno = gno;
                this.title = title;
                this.content = content;
                this.writer = writer;
                this.regDate = regDate;
                this.modDate = modDate;
            }

            public static GuestBookDTO.Response.Read toDto(GuestBook entity){
                return GuestBookDTO.Response.Read.builder()
                    .gno(entity.getGno())
                    .title(entity.getTitle())
                    .content(entity.getContent())
                    .writer(entity.getWriter())
                    .regDate(entity.getRegDate())
                    .modDate(entity.getModDate())
                    .build();
            }
        } //Read

    }

}
