package com.simplecoding.simlesuggestion.es.gallerysuggested.service;

import com.simplecoding.simlesuggestion.common.ErrorMsg;
import com.simplecoding.simlesuggestion.common.MapStruct;
import com.simplecoding.simlesuggestion.es.gallerysuggested.dto.GallerySuggestedDto;
import com.simplecoding.simlesuggestion.es.gallerysuggested.entity.GallerySuggested;
import com.simplecoding.simlesuggestion.es.gallerysuggested.repository.GallerySuggestedRepository;
import com.simplecoding.simlesuggestion.jpa.auth.entity.Member;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Log4j2
@Service
@RequiredArgsConstructor
public class GallerySuggestedService {
    private final GallerySuggestedRepository gallerySuggestedRepository;
    private final MapStruct mapStruct;
    private final ErrorMsg errorMsg;

    //    상세조회
    public GallerySuggestedDto findById() {
        // SecurityContext에서 로그인된 유저 가져오기
        Member member = (Member)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        log.info(member.getEmail());

        GallerySuggested gallerySuggested = gallerySuggestedRepository.findById(member.getEmail())
                .orElseThrow(() -> new RuntimeException(errorMsg.getMessage("errors.es.not.found")));
        return mapStruct.toDto(gallerySuggested);
    }
}
