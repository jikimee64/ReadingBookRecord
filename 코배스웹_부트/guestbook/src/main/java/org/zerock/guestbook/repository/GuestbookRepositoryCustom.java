package org.zerock.guestbook.repository;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.zerock.guestbook.dto.GuestBookDto;
import org.zerock.guestbook.dto.GuestBookSearchCondition;

public interface GuestbookRepositoryCustom {
   // List<GuestBookDto> search(GuestBookSearchCondition condition);

    Page<GuestBookDto> searchPageAllAnd(GuestBookSearchCondition condition, Pageable pageable);

    Page<GuestBookDto> searchPageTitleOrContentAndGnoGt(GuestBookSearchCondition condition, Pageable pageable);

 /*   Page<GuestBookDto> searchPageComplex(GuestBookSearchCondition condition,
        Pageable pageable);*/
}
