package org.zerock.board.service;


import java.util.ArrayList;
import java.util.List;
import org.zerock.board.dto.ReplyDTO;
import org.zerock.board.entity.Board;
import org.zerock.board.entity.Reply;
import org.zerock.board.repository.BoardRepository;

public abstract class ReplyService {

    private BoardRepository boardRepository;

    Long register(ReplyDTO replyDTO){
        return 1L;
    }

    List<ReplyDTO> getList(Long bno){
        return new ArrayList();
    }

    void modify(ReplyDTO replyDTO){}

    void remove(Long rno){}

    //ReplyDTO를 Reply 객체로 변환 Board객체의 처리가 수반
    Reply dtoToENtity(ReplyDTO dto){
        Board board = boardRepository.findById(dto.getBno()).get();
        Reply reply = Reply.builder()
            .text(dto.getText())
            .replyer(dto.getReplyer())
            .board(board)
            .build();
        return reply;
    }

    //Reply 객체를 ReplyDTO로 변환 Board 객체가 필요하지 않으므로 게시물 번호만
    ReplyDTO entityToDTO(Reply reply){
        return ReplyDTO.builder()
            .rno(reply.getId())
            .text(reply.getText())
            .replyer(reply.getReplyer())
            .regDate(reply.getRegDate())
            .modDate(reply.getModDate())
            .build();
    }

}