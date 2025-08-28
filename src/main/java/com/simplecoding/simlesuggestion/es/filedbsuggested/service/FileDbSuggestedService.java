package com.simplecoding.simlesuggestion.es.filedbsuggested.service;

import com.simplecoding.simlesuggestion.common.ErrorMsg;
import com.simplecoding.simlesuggestion.common.MapStruct;
import com.simplecoding.simlesuggestion.es.filedbsuggested.dto.FileDbSuggestedDto;
import com.simplecoding.simlesuggestion.es.filedbsuggested.entity.FileDbSuggested;
import com.simplecoding.simlesuggestion.es.filedbsuggested.repository.FileDbSuggestedRepository;
import com.simplecoding.simlesuggestion.jpa.auth.entity.Member;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Log4j2
@Service
@RequiredArgsConstructor
public class FileDbSuggestedService {

    private final FileDbSuggestedRepository fileDbSuggestedRepository;
    private final MapStruct mapStruct;
    private final ErrorMsg errorMsg;

    //    상세조회
    public FileDbSuggestedDto findById() {
        // SecurityContext에서 인증된 사용자 가져오기
        Member member = (Member)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        log.info(member.getEmail());

        FileDbSuggested fileDbSuggested = fileDbSuggestedRepository.findById(member.getEmail())
                .orElseThrow(() -> new RuntimeException(errorMsg.getMessage("errors.es.not.found")));
        return mapStruct.toDto(fileDbSuggested);
    }
}
