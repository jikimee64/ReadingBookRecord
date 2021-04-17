package org.zerock.board.service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.zerock.board.dto.BoardDTO;
import org.zerock.board.dto.PageRequestDTO;
import org.zerock.board.dto.PageResultDTO;

@SpringBootTest
class BoardServiceTest {

    @Autowired
    private BoardService boardService;

    @Test
    public void testRegister(){
        BoardDTO.Request.Save dto = BoardDTO.Request.Save.builder()
            .title("Test.")
            .content("Test...")
            .email("user55@aaa.com") // 현재 DB에 존재하는 회원 이메일
            .build();

        Long bno = boardService.register(dto);
    }

    @Test
    public void testList(){
        PageRequestDTO pageRequestDTO = new PageRequestDTO();

        PageResultDTO<BoardDTO.Response.List, Object[]> result = boardService.getList(pageRequestDTO);

        for(BoardDTO.Response.List dto : result.getDtoList()){
            System.out.println(dto);
        }
    }

}