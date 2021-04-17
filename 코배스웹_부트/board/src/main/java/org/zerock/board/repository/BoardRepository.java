package org.zerock.board.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.zerock.board.dto.BoardDTO;
import org.zerock.board.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board, Long> {
    // 한개의 로우(Object) 내에 Object[]로 나옴
    // 내부에 있는 엔티티를 이용할 때는 'LEFT JOIN'뒤에 'ON'을 이용하는 부분이 없다.
    @Query("select b, w from Board b left join b.member w where b.id =:id")
    Optional<Board> getBoardWithWriter(@Param("id") Long id);

    // Board입장에서 Reply 객체를 참조하고 있지 않기 때문에 'on'을 이용
    @Query("select b, r from Board b LEFT JOIN Reply r ON r.board = b WHERE b.id = :id")
    List<Object[]> getBoardWithReply(@Param("id") Long id);

    /**
     * countQuery를 작성하지 않았을시 Pageable 동작하지 않음
     * countQuery : 조회 건수에 대한 쿼리문
     * Object -> DTO 이용
     */
    //@Query(value = "select new org.zerock.board.dto.BoardDTO.Response.List(b.id, b.regDate, b.title, m.name, m.email, count(r))" +
      @Query(value = "SELECT b, m, count(r)" +
        " FROM Board b " +
        " LEFT JOIN b.member m " +
        " LEFT JOIN Reply r ON r.board = b " +
        " GROUP BY b",
        countQuery = "SELECT count(b) FROM Board b")
    Page<Object[]> getBoardWithReplyCount(Pageable pageable);

    // Object -> DTO 이용
   // @Query(value = "select new org.zerock.board.dto.BoardDTO.Response.Detail(b.id, b.regDate, b.modDate, b.title, b.content, w.name, w.email, count(r))" +\
    @Query(value = "SELECT b, m, count(r)" +
        " FROM Board b LEFT JOIN b.member m " +
        " LEFT OUTER JOIN Reply r ON r.board = b" +
        " WHERE b.id = :id")
    BoardDTO.Response.Detail getBoardByBno(@Param("id") Long id);


}
