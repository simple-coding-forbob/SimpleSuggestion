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

        // 인증 토큰 만들기
        UsernamePasswordAuthenticationToken auth =
                new UsernamePasswordAuthenticationToken(member, member.getPassword());

        // SecurityContextHolder에 주입
        SecurityContextHolder.getContext().setAuthentication(auth);
    }

    @Test
    void findById() {
        FileDbSuggestedDto fileDbSuggestedDto= fileDbSuggestedService.findById();
        log.info(fileDbSuggestedDto);
    }
}