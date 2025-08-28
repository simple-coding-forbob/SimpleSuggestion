package com.simplecoding.simlesuggestion.common;

import com.simplecoding.simlesuggestion.jpa.auth.dto.SecurityUserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SecurityUtil {
    private final ErrorMsg errorMsg;

    public SecurityUserDto getLoginUser() {
//        시큐리티 홀더에서 유저 꺼내기
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        꺼낸 유저가 시큐리유저 또는 소셜로그인 유저 일 수 있음
        if (principal instanceof SecurityUserDto user) {
            return user;
        }
        throw new RuntimeException(errorMsg.getMessage("errors.unauthorized"));
    }
}
