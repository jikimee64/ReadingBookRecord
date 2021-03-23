package org.zerock.guestbook.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.zerock.guestbook.dto.PageRequestDTO;
import org.zerock.guestbook.entity.GuestBook;

public interface GuestbookRepositoryCustom {
   // List<GuestBookDto> search(GuestBookSearchCondition condition);

//    Page<GuestBookDTO> searchPageAllAnd(GuestBookSearchCondition condition, Pageable pageable);
//
//    Page<GuestBookDTO> searchPageTitleOrContentOrWriterAndGnoGt(GuestBookSearchCondition condition, Pageable pageable);


    Page<GuestBook> searchPageAllAnd(PageRequestDTO requestDTO, Pageable pageable);

    Page<GuestBook> searchPageTitleOrContentOrWriterAndGnoGt(PageRequestDTO requestDTO, Pageable pageable);

 /*   Page<GuestBookDto> searchPageComplex(GuestBookSearchCondition condition,
        Pageable pageable);*/
}
