package com.simplecoding.simlesuggestion.jpa.filedblikes.controller;

import com.simplecoding.simlesuggestion.jpa.filedblikes.dto.FileDbLikesDto;
import com.simplecoding.simlesuggestion.jpa.filedblikes.service.FileDbLikesService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@Log4j2
@RestController
@RequiredArgsConstructor
public class FileDbLikesController {
    private final FileDbLikesService fileDbLikesService;

    //    저장
//	insert : 저장 버튼 클릭시
    @PostMapping("/api/filedb/likes/add")
    public void insert(@ModelAttribute FileDbLikesDto fileDbLikesDto) {
//		Dept 내용 확인
        log.info("ajax 테스트 :" + fileDbLikesDto);
//		서비스의 insert 실행
        fileDbLikesService.save(fileDbLikesDto);
    }
}
