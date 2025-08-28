package com.simplecoding.simlesuggestion.es.filedbsuggested.service;

import com.simplecoding.simlesuggestion.es.filedbsuggested.dto.FileDbSuggestedDto;
import com.simplecoding.simlesuggestion.jpa.auth.entity.Member;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;

@Log4j2
@SpringBootTest
class FileDbSuggestedServiceTest {

    @Autowired
    FileDbSuggestedService fileDbSuggestedService;

    @BeforeEach
    void setUp() {
        // 가짜 유저 생성
        Member member = new Member("forbob@naver.com", "123456", "forbob", "ROLE_ADMIN");

        // 로그인 필터 수동 검증
        UsernamePasswordAuthenticationToken auth =
                new UsernamePasswordAuthenticationToken(member, member.getPassword());

        // 검증된 객체 SecurityContextHolder에 넣기: 로그인 된 상태가 됨(인증 에러 없음)
        SecurityContextHolder.getContext().setAuthentication(auth);
    }

    @Test
    void findById() {
        FileDbSuggestedDto fileDbSuggestedDto= fileDbSuggestedService.findById();
        log.info(fileDbSuggestedDto);
    }
}