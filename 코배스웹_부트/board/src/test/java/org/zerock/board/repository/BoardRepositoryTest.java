package org.zerock.board.repository;

import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.zerock.board.dto.BoardDTO;
import org.zerock.board.entity.Board;
import org.zerock.board.entity.Member;
import java.util.stream.IntStream;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BoardRepositoryTest {

    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Test
    @Disabled
    @DisplayName("게시글 더미데이터")
    void insertBoard(){
        IntStream.rangeClosed(1, 100).forEach(i -> {
            Member member = memberRepository.findById((long) i)
                .orElseThrow(() -> new IllegalStateException("존재하지 않는 회원입니다."));

            Board board = Board.builder()
                .title("Title..."+i)
                .content("Content...."+i)
                .member(member)
                .build();

            boardRepository.save(board);
        });
    }

    @Test
    @DisplayName("게시글 + 회원")
    void testReadWithWriter(){
        Board board = boardRepository.getBoardWithWriter(100L)
            .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 게시글입니다."));

        System.out.println("------------------------");
        System.out.println(board.toString());
        System.out.println(board.getMember().toString());

    }

    @Test
    @DisplayName("게시글 + 댓글")
    void testGetBoardWithReply(){
        List<Object[]> result = boardRepository.getBoardWithReply(100L);

        result.forEach( object -> {
            System.out.println(Arrays.toString(object));
        });
    }

    @Test
    @DisplayName("게시글 + 회원 + 댓글 / 목록페이지")
    void testWithReplyCount(){
        Pageable pageable = PageRequest.of(0, 10, Sort.by("id").descending());
        Page<Object[]> result = boardRepository.getBoardWithReplyCount(pageable);
        result.forEach( row -> {
            System.out.println(row.toString());
        });
    }

    @Test
    @DisplayName("게시글 + 회원 + 댓글 / 상세페이지")
    void testRead3(){
        BoardDTO.Response.Detail result = boardRepository.getBoardByBno(100L);
        System.out.println(result.toString());
    }

}