package org.zerock.board.repository;

import java.util.List;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.zerock.board.entity.Board;
import org.zerock.board.entity.Reply;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReplyRepository extends JpaRepository<Reply, Long> {

    @Modifying(clearAutomatically = true)
    @Query("delete from Reply r where r.board.id = :id")
    void deleteByBoardId(Long id);

    List<Reply> getRepliesByBoardOrderById(Board board);

}