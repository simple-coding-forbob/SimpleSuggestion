package com.simplecoding.simlesuggestion.es.gallerysuggested.service;

import com.simplecoding.simlesuggestion.common.ErrorMsg;
import com.simplecoding.simlesuggestion.common.MapStruct;
import com.simplecoding.simlesuggestion.es.gallerysuggested.dto.GallerySuggestedDto;
import com.simplecoding.simlesuggestion.es.gallerysuggested.entity.GallerySuggested;
import com.simplecoding.simlesuggestion.es.gallerysuggested.repository.GallerySuggestedRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GallerySuggestedService {
    private final GallerySuggestedRepository gallerySuggestedRepository;
    private final MapStruct mapStruct;
    private final ErrorMsg errorMsg;

    //    상세조회
    public GallerySuggestedDto findById() {
        // SecurityContext에서 인증된 사용자 가져오기
        String email = SecurityContextHolder.getContext().getAuthentication().getName();

        GallerySuggested gallerySuggested = gallerySuggestedRepository.findById(email)
                .orElseThrow(() -> new RuntimeException(errorMsg.getMessage("errors.es.not.found")));
        return mapStruct.toDto(gallerySuggested);
    }
}
