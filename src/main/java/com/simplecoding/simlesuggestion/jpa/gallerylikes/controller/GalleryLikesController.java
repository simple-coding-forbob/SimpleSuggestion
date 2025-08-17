package com.simplecoding.simlesuggestion.jpa.gallerylikes.controller;

import com.simplecoding.simlesuggestion.jpa.gallerylikes.dto.GalleryLikesDto;
import com.simplecoding.simlesuggestion.jpa.gallerylikes.service.GalleryLikesService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@Log4j2
@RestController
@RequiredArgsConstructor
public class GalleryLikesController {
    private final GalleryLikesService galleryLikesService;

    //    저장
//	insert : 저장 버튼 클릭시
    @PostMapping("/api/gallery/likes/add")
    public void insert(@ModelAttribute GalleryLikesDto galleryLikesDto) {
//		Dept 내용 확인
        log.info("ajax 테스트 :" + galleryLikesDto);
//		서비스의 insert 실행
        galleryLikesService.save(galleryLikesDto);
    }
}
