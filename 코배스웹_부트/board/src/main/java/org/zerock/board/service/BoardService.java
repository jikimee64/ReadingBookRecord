package org.zerock.board.service;

import java.util.function.Function;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zerock.board.dto.BoardDTO;
import org.zerock.board.dto.BoardDTO.Request.Save;
import org.zerock.board.dto.BoardDTO.Response.Detail;
import org.zerock.board.dto.BoardDTO.Response.List;
import org.zerock.board.dto.PageRequestDTO;
import org.zerock.board.dto.PageResultDTO;
import org.zerock.board.entity.Board;
import org.zerock.board.entity.Member;
import org.zerock.board.repository.BoardRepository;
import org.zerock.board.repository.MemberRepository;
import org.zerock.board.repository.ReplyRepository;

@Transactional(readOnly = true)
@Service
@Slf4j
public class BoardService {

    private final BoardRepository boardRepository;
    private final MemberRepository memberRepository;
    private final ReplyRepository replyRepository;

    public BoardService(BoardRepository boardRepository,
        MemberRepository memberRepository, ReplyRepository replyRepository) {
        this.boardRepository = boardRepository;
        this.memberRepository = memberRepository;
        this.replyRepository = replyRepository;
    }

    @Transactional
    public Long register(BoardDTO.Request.Save dto){
        Member member = memberRepository.findByEmail(dto.getEmail());
        Board board = Save.dtoToEntity(dto, member);
        log.info("----- board {}", board);
        boardRepository.save(board);
        return board.getId();
    }

    public PageResultDTO<BoardDTO.Response.List, Object[]> getList(PageRequestDTO pageRequestDTO){
        log.info("pageResultDTO : {}", pageRequestDTO);

        Function<Object[], BoardDTO.Response.List> fn = ( en -> List.entityToDTO((Board)en[0], (Member)en[1], (Long)en[2]));

        log.info("fn : {}", fn);

        Page<Object[]> result = boardRepository.getBoardWithReplyCount(
            pageRequestDTO.getPageable(Sort.by("id").descending())
        );
        log.info("result : {}", result);

        return new PageResultDTO(result, fn);

    }

    public BoardDTO.Response.Detail get(Long bno){
        Detail detail = boardRepository.getBoardByBno(bno);
        return Detail.entityToDTO(Detail.board(detail), Detail.member(detail), detail.getCountReply());
    }


    @Transactional
    public void removeWithReplies(Long id){
        replyRepository.deleteByBoardId(id);
        boardRepository.deleteById(id);
    }

    public void modify(BoardDTO.Request.Modify dto){
        //getOne() : 필요한 순간까지 로딩을 지연
        Board board = boardRepository.getOne(dto.getId());
        if(board != null){
            board.changeTitle(dto.getTitle());
            board.changeContent(dto.getContent());

            boardRepository.save(board);
        }
    }

}