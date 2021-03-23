package org.zerock.board.repository;

import org.junit.jupiter.api.DisplayName;
import org.zerock.board.entity.Member;
import java.util.stream.IntStream;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MemberRepositoryTest {

    @Autowired
    private MemberRepository memberRepository;

    @Test
    @DisplayName("회원 더미데이터")
    void insertMembers(){
        IntStream.rangeClosed(1, 100).forEach(i -> {
            Member member = Member.builder()
                .email("user"+i+"@aaa.com")
                .password("1111")
                .name("USER"+i)
                .build();

            memberRepository.save(member);
        });
    }

}