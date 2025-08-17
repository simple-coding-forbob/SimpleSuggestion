package com.simplecoding.simlesuggestion.jpa.filedblikes.service;

import com.simplecoding.simlesuggestion.common.ErrorMsg;
import com.simplecoding.simlesuggestion.common.MapStruct;
import com.simplecoding.simlesuggestion.jpa.filedblikes.dto.FileDbLikesDto;
import com.simplecoding.simlesuggestion.jpa.filedblikes.entity.FileDbLikes;
import com.simplecoding.simlesuggestion.jpa.filedblikes.repository.FileDbLikesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class FileDbLikesService {
    private final FileDbLikesRepository fileDbLikesRepository;
    private final MapStruct mapStruct;
    private final ErrorMsg errorMsg;

    //    저장
    public void save(FileDbLikesDto fileDbLikesDto) {

//        좋아요 이미 했는 지 확인:
        if (fileDbLikesRepository.countEmailAndUuid(fileDbLikesDto.getEmail(), fileDbLikesDto.getUuid()) > 0) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, errorMsg.getMessage("errors.likes"));
        }

    //        JPA 저장 함수 실행 : return 값 : 저장된 객체
        FileDbLikes fileDbLikes=mapStruct.toEntity(fileDbLikesDto);
        fileDbLikesRepository.save(fileDbLikes);
    }
}
