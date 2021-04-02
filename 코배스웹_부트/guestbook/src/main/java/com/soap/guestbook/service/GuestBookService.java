package com.soap.guestbook.service;

import com.soap.guestbook.dto.GuestBookDTO.Request.Modify;
import com.soap.guestbook.dto.GuestBookDTO.Response.Read;
import com.soap.guestbook.repository.GuestbookRepository;
import java.util.Optional;
import java.util.function.Function;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import com.soap.guestbook.dto.PageRequestDTO;
import com.soap.guestbook.dto.PageResultDTO;
import com.soap.guestbook.entity.GuestBook;

@Slf4j
@Service
@RequiredArgsConstructor
public class GuestBookService {

    private final GuestbookRepository repository;

    public Long register(GuestBook entity){

        GuestBook save = repository.save(entity);

        return save.getGno();
    }

    public PageResultDTO<Read, GuestBook> getList(PageRequestDTO requestDTO){

        Pageable pageable = requestDTO.getPageable(Sort.by("gno").descending());

        String type = requestDTO.getType();

        Function<GuestBook, Read> fn = (Read::toDto);
        if(type == null || type.trim().length() == 0){
            Page<GuestBook> result = repository.findAll(pageable);
            return new PageResultDTO<>(result, fn);
        }

        Page<GuestBook> result = repository.searchPageTitleOrContentOrWriterAndGnoGt(requestDTO, pageable);
        return new PageResultDTO<>(result, fn);
    }

    public Read read(Long gno){
        Optional<GuestBook> result = repository.findById(gno);

        return result.map(Read::toDto).orElse(null);
        //return result.isPresent() ? GuestBookDTO.Response.Read.toDto(result.get()) : null;
    }

    public void remove(Long gno){
        repository.deleteById(gno);
    }

    public void modify(Modify dto) {

        //업데이트 하는 항목은 '제목', '내용'

        Optional<GuestBook> result = repository.findById(dto.getGno());

        if(result.isPresent()){

            GuestBook entity = result.get();

            entity.changeTitle(dto.getTitle());
            entity.changeContent(dto.getContent());

            repository.save(entity);
        }
    }


}
