package com.simplecoding.simlesuggestion.es.gallerysuggested.service;

import com.simplecoding.simlesuggestion.common.ErrorMsg;
import com.simplecoding.simlesuggestion.common.MapStruct;
import com.simplecoding.simlesuggestion.common.SecurityUtil;
import com.simplecoding.simlesuggestion.es.gallerysuggested.dto.GallerySuggestedDto;
import com.simplecoding.simlesuggestion.es.gallerysuggested.entity.GallerySuggested;
import com.simplecoding.simlesuggestion.es.gallerysuggested.repository.GallerySuggestedRepository;
import com.simplecoding.simlesuggestion.jpa.auth.dto.SecurityUserDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Log4j2
@Service
@RequiredArgsConstructor
public class GallerySuggestedService {
    private final GallerySuggestedRepository gallerySuggestedRepository;
    private final MapStruct mapStruct;
    private final ErrorMsg errorMsg;
    private final SecurityUtil securityUtil;    // 로긴 유저 가져오기 공통 클래스

    //    상세조회
    public GallerySuggestedDto findById() {
        // Spring Security 에서 로그인된 유저 가져오기
        SecurityUserDto securityUserDto = securityUtil.getLoginUser();

        GallerySuggested gallerySuggested = gallerySuggestedRepository.findById(securityUserDto.getUsername())
                .orElseThrow(() -> new RuntimeException(errorMsg.getMessage("errors.es.not.found")));
        return mapStruct.toDto(gallerySuggested);
    }
}
