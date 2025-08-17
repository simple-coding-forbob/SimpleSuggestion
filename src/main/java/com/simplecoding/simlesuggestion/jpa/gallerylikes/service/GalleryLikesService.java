package com.simplecoding.simlesuggestion.jpa.gallerylikes.service;

import com.simplecoding.simlesuggestion.common.ErrorMsg;
import com.simplecoding.simlesuggestion.common.MapStruct;
import com.simplecoding.simlesuggestion.jpa.gallerylikes.dto.GalleryLikesDto;
import com.simplecoding.simlesuggestion.jpa.gallerylikes.entity.GalleryLikes;
import com.simplecoding.simlesuggestion.jpa.gallerylikes.repository.GalleryLikesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class GalleryLikesService {
    private final GalleryLikesRepository galleryLikesRepository;
    private final MapStruct mapStruct;
    private final ErrorMsg errorMsg;

    //    저장
    public void save(GalleryLikesDto galleryLikesDto) {

//        좋아요 이미 했는 지 확인:
        if (galleryLikesRepository.countEmailAndUuid(galleryLikesDto.getEmail(), galleryLikesDto.getUuid()) > 0) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, errorMsg.getMessage("errors.likes"));
        }

    //        JPA 저장 함수 실행 : return 값 : 저장된 객체
        GalleryLikes galleryLikes=mapStruct.toEntity(galleryLikesDto);
        galleryLikesRepository.save(galleryLikes);
    }
}
