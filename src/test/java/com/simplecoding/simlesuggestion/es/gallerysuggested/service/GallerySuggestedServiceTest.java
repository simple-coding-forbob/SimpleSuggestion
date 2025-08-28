package com.simplecoding.simlesuggestion.es.gallerysuggested.service;

import com.simplecoding.simlesuggestion.es.gallerysuggested.dto.GallerySuggestedDto;
import com.simplecoding.simlesuggestion.jpa.auth.dto.SecurityUserDto;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.HashSet;
import java.util.Set;

@Log4j2
@SpringBootTest
class GallerySuggestedServiceTest {

    @Autowired
    GallerySuggestedService gallerySuggestedService;

    //  TODO: 테스트 하기 전에 항상 실행되는 메소드입니다.
    @BeforeEach
    void setUp() {
        String email="forbob@naver.com";  // 계정
        String password="123456";         // 암호
        GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_ADMIN");
        Set<GrantedAuthority> authorities = new HashSet<>();
        authorities.add(authority);       // 권한들

//      시큐리티 유저 준비
        SecurityUserDto securityUserDto = new SecurityUserDto(email, password, authorities);
        // 인증 유저 만들기
        UsernamePasswordAuthenticationToken auth =
                new UsernamePasswordAuthenticationToken(securityUserDto, securityUserDto.getPassword());
        // 로그인 된 상태가 됩니다.
        SecurityContextHolder.getContext().setAuthentication(auth);
    }

    @Test
    void findById() {
        GallerySuggestedDto gallerySuggestedDto= gallerySuggestedService.findById();
        log.info(gallerySuggestedDto);
    }
}