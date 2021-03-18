package org.zerock.guestbook.repository;

import static org.thymeleaf.util.StringUtils.isEmpty;
import static org.zerock.guestbook.entity.QGuestBook.guestBook;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.QueryResults;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import javax.persistence.EntityManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.zerock.guestbook.dto.GuestBookDto;
import org.zerock.guestbook.dto.GuestBookSearchCondition;

public class GuestbookRepositoryImpl implements GuestbookRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public GuestbookRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public Page<GuestBookDto> searchPageAllAnd(GuestBookSearchCondition condition,
        Pageable pageable) {
        QueryResults<GuestBookDto> results = queryFactory
            .select(Projections.constructor(GuestBookDto.class,
                guestBook.content,
                guestBook.title,
                guestBook.writer))
            .from(guestBook)
            .where(contentEq(condition.getContent()),
                titleEq(condition.getTitle()),
                writerEq(condition.getWriter()))
            .offset(pageable.getOffset())
            .limit(pageable.getPageSize())
            .fetchResults();

        List<GuestBookDto> content = results.getResults();
        long total = results.getTotal();

        return new PageImpl<>(content, pageable, total);
    }

    @Override
    public Page<GuestBookDto> searchPageTitleOrContentAndGnoGt(GuestBookSearchCondition condition,
        Pageable pageable) {
        QueryResults<GuestBookDto> results = queryFactory
            .select(Projections.constructor(GuestBookDto.class,
                guestBook.content,
                guestBook.title,
                guestBook.writer))
            .from(guestBook)
            /*.where(contentEq(condition.getContent()),
                titleEq(condition.getTitle()),
                writerEq(condition.getWriter()))*/
            .where(contentOrTitle(condition.getContent(),condition.getTitle()),
                guestBook.gno.gt(0L))
            .offset(pageable.getOffset())
            .limit(pageable.getPageSize())
            .fetchResults();

        List<GuestBookDto> content = results.getResults();
        long total = results.getTotal();

        return new PageImpl<>(content, pageable, total);
    }

    private BooleanExpression contentEq(String content) {
        return isEmpty(content) ? null : guestBook.content.contains(content);
    }

    private BooleanExpression titleEq(String title) {
        return isEmpty(title) ? null : guestBook.content.contains(title);
    }

    private BooleanExpression writerEq(String writer) {
        return isEmpty(writer) ? null : guestBook.content.contains(writer);
    }

    //2개이상 조합(content + title)
    private BooleanBuilder contentOrTitle(String content, String title){
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        return booleanBuilder
            .and(contentEq(content))
            .or(titleEq(title));
    }


}
