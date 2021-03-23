package org.zerock.board.repository;

import org.junit.jupiter.api.DisplayName;
import org.springframework.transaction.annotation.Transactional;
import org.zerock.board.entity.Board;
import org.zerock.board.entity.Reply;
import java.util.stream.IntStream;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.util.ReflectionTestUtils;

@SpringBootTest
//@Transactional
class ReplyRepositoryTest {

    @Autowired
    private ReplyRepository replyRepository;

    @Autowired
    private BoardRepository boardRepository;

    @Test
    @DisplayName("댓글 더미데이터")
    void insertReply(){
        IntStream.rangeClosed(1, 300).forEach(i -> {

            long bno = (long)(Math.random() * 100) + 1;

            Board board = boardRepository.findById(bno)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 게시글입니다."));

            Reply reply = Reply.builder()
                .text("Reply....."+i)
                .board(board)
                .replyer("guest")
                .build();

            replyRepository.save(reply);

        });
    }

}