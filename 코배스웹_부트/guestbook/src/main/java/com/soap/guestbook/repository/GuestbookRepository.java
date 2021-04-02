package com.soap.guestbook.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.soap.guestbook.entity.GuestBook;

public interface GuestbookRepository extends JpaRepository<GuestBook, Long>,
    GuestbookRepositoryCustom {

}
