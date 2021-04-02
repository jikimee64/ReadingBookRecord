package com.soap.guestbook.repository;

import com.querydsl.core.QueryResults;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.soap.guestbook.entity.GuestBook;
import com.soap.guestbook.entity.QGuestBook;
import java.util.List;
import javax.persistence.EntityManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import com.soap.guestbook.dto.PageRequestDTO;

public class GuestbookRepositoryImpl implements GuestbookRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public GuestbookRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public Page<GuestBook> searchPageAllAnd(PageRequestDTO requestDTO, Pageable pageable) {
        QueryResults<GuestBook> results = queryFactory
            .select(Projections.fields(GuestBook.class,
                QGuestBook.guestBook.gno,
                QGuestBook.guestBook.content,
                QGuestBook.guestBook.title,
                QGuestBook.guestBook.writer,
                QGuestBook.guestBook.regDate))
            .from(QGuestBook.guestBook)
            .where(contentEq(requestDTO),
                titleEq(requestDTO),
                writerEq(requestDTO))
            .offset(pageable.getOffset())
            .limit(pageable.getPageSize())
            .fetchResults();

        List<GuestBook> content = results.getResults();
        long total = results.getTotal();

        return new PageImpl<>(content, pageable, total);
    }

    @Override
    public Page<GuestBook> searchPageTitleOrContentOrWriterAndGnoGt(PageRequestDTO requestDTO,
        Pageable pageable) {
        QueryResults<GuestBook> results = queryFactory
            .select(Projections.fields(GuestBook.class,
                QGuestBook.guestBook.gno,
                QGuestBook.guestBook.content,
                QGuestBook.guestBook.title,
                QGuestBook.guestBook.writer,
                QGuestBook.guestBook.regDate))
            .from(QGuestBook.guestBook)
            .where(contentEq(requestDTO),
                titleEq(requestDTO),
                writerEq(requestDTO),
            //.where(contentOrTitleOrWriter(condition.getContent(),condition.getTitle(),condition.getWriter()),
                QGuestBook.guestBook.gno.gt(0L))
            .offset(pageable.getOffset())
            .limit(pageable.getPageSize())
            .fetchResults();

        List<GuestBook> content = results.getResults();
        long total = results.getTotal();

        return new PageImpl<>(content, pageable, total);
    }

    private BooleanExpression titleEq(PageRequestDTO requestDTO) {
        String type = requestDTO.getType();
        String title = requestDTO.getKeyword();

        return type.contains("t") ? QGuestBook.guestBook.content.contains(title) : null;
    }

    private BooleanExpression contentEq(PageRequestDTO requestDTO) {
        String type = requestDTO.getType();
        String content = requestDTO.getKeyword();

        return type.contains("c") ? QGuestBook.guestBook.content.contains(content) : null;
    }

    private BooleanExpression writerEq(PageRequestDTO requestDTO) {
        String type = requestDTO.getType();
        String writer = requestDTO.getKeyword();

        return type.contains("w") ? QGuestBook.guestBook.content.contains(writer) : null;
    }

    //2개이상 조합(content + title)
//    private BooleanBuilder contentOrTitleOrWriter(String content, String title, String writer){
//        BooleanBuilder booleanBuilder = new BooleanBuilder();
//        return booleanBuilder
//            .or(contentEq(content))
//            .or(titleEq(title))
//            .or(writerEq(writer));
//    }

}