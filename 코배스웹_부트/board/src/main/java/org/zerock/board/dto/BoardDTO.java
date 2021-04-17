package org.zerock.board.dto;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.zerock.board.entity.Board;
import org.zerock.board.entity.Member;

@Data
public class BoardDTO {

    public static class Request {

        @Data
        @Builder
        @AllArgsConstructor
        @NoArgsConstructor
        public static class Save {

            private Long id;
            private LocalDateTime regDate;
            private LocalDateTime modDate;
            private String title;
            private String content;
            private String email; // 작성자의 이메일(id)
            private String name;  // 작성자의 이름
            private int countReply; // 해당 게시글의 댓글 수

            public static Board dtoToEntity(BoardDTO.Request.Save dto,
                Member member) {
                return Board.builder()
                    .title(dto.getTitle())
                    .content(dto.getContent())
                    .member(member)
                    .build();

            }
        }
    }

    public static class Response {

        @Data
        @Builder
        @NoArgsConstructor
        public static class List {

            private Long id;
            private LocalDateTime regDate;
            private String title;
            private String name;  // 작성자의 이름
            private String email; // 작성자의 이메일(id)
            private int countReply; // 해당 게시글의 댓글 수

            public List(Long id, LocalDateTime regDate, String title, String name, String email,
                int countReply) {
                this.id = id;
                this.regDate = regDate;
                this.title = title;
                this.name = name;
                this.email = email;
                this.countReply = countReply;
            }

            public static List entityToDTO(Board board, Member member, Long replyCount) {
                return List.builder()
                    .id(board.getId())
                    .regDate(board.getRegDate())
                    .title(board.getTitle())
                    .name(member.getName())
                    .email(member.getEmail())
                    .countReply(replyCount.intValue()) // long -> int
                    .build();
            }
        }

        @Data
        @Builder
        @NoArgsConstructor
        public static class Detail {

            private Long id;
            private LocalDateTime regDate;
            private LocalDateTime modDate;
            private String title;
            private String content;
            private String name;  // 작성자의 이름
            private String email; // 작성자의 이메일(id)
            private int countReply; // 해당 게시글의 댓글 수

            public Detail(Long id, LocalDateTime regDate, LocalDateTime modDate, String title,
                String content, String name, String email, int countReply) {
                this.id = id;
                this.regDate = regDate;
                this.modDate = modDate;
                this.title = title;
                this.content = content;
                this.name = name;
                this.email = email;
                this.countReply = countReply;
            }

            public static Detail entityToDTO(Board board, Member member, Long replyCount) {
                return Detail.builder()
                    .id(board.getId())
                    .title(board.getTitle())
                    .content(board.getContent())
                    .regDate(board.getRegDate())
                    .modDate(board.getModDate())
                    .email(member.getEmail())
                    .name(member.getName())
                    .countReply(replyCount.intValue()) // long -> int
                    .build();
            }

        }

    }


}
