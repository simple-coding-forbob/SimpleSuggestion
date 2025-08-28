package com.simplecoding.simlesuggestion.es.gallerysuggested.service;

import com.simplecoding.simlesuggestion.es.gallerysuggested.dto.GallerySuggestedDto;
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
class GallerySuggestedServiceTest {

    @Autowired
    GallerySuggestedService gallerySuggestedService;

    @BeforeEach
    void setUp() {
        // 가짜 유저 생성
        Member member = new Member("forbob@naver.com", "123456", "forbob", "ROLE_USER");
        // 인증 토큰 만들기
        UsernamePasswordAuthenticationToken auth =
                new UsernamePasswordAuthenticationToken(member, member.getPassword());
        // SecurityContextHolder에 저장함: 로그인된 유저는 여기에 넣어둠, 여기에 있으면 로그인된 것으로 인식하고 인증 에러 안남
        SecurityContextHolder.getContext().setAuthentication(auth);
    }

    @Test
    void findById() {
        GallerySuggestedDto gallerySuggestedDto= gallerySuggestedService.findById();
        log.info(gallerySuggestedDto);
    }
}