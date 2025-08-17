package com.simplecoding.simlesuggestion.es.filedbsuggested.service;

import com.simplecoding.simlesuggestion.common.ErrorMsg;
import com.simplecoding.simlesuggestion.common.MapStruct;
import com.simplecoding.simlesuggestion.es.filedbsuggested.dto.FileDbSuggestedDto;
import com.simplecoding.simlesuggestion.es.filedbsuggested.entity.FileDbSuggested;
import com.simplecoding.simlesuggestion.es.filedbsuggested.repository.FileDbSuggestedRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FileDbSuggestedService {

    private final FileDbSuggestedRepository fileDbSuggestedRepository;
    private final MapStruct mapStruct;
    private final ErrorMsg errorMsg;

    //    상세조회
    public FileDbSuggestedDto findById() {
        // SecurityContext에서 인증된 사용자 가져오기
        String email = SecurityContextHolder.getContext().getAuthentication().getName();

        FileDbSuggested fileDbSuggested = fileDbSuggestedRepository.findById(email)
                .orElseThrow(() -> new RuntimeException(errorMsg.getMessage("errors.es.not.found")));
        return mapStruct.toDto(fileDbSuggested);
    }
}
