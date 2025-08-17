package com.simplecoding.simlesuggestion.filedblikes.service;

import com.simplecoding.simlesuggestion.jpa.filedblikes.dto.FileDbLikesDto;
import com.simplecoding.simlesuggestion.jpa.filedblikes.service.FileDbLikesService;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Log4j2
@SpringBootTest
class FileDbLikesServiceTest {

    @Autowired
    FileDbLikesService fileDbLikesService;

    @Test
    void save() {
        FileDbLikesDto fileDbLikesDto=new FileDbLikesDto();
        fileDbLikesDto.setEmail("forbob@naver.com");
        fileDbLikesDto.setUuid("12345671");
        fileDbLikesDto.setLikeCount((long)1);

        fileDbLikesService.save(fileDbLikesDto);
    }
}