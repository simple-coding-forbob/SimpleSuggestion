package com.simplecoding.simlesuggestion.es.lookup.service;

import com.simplecoding.simlesuggestion.es.lookup.dto.LookupAllDto;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

@Log4j2
@SpringBootTest
class LookupAllServiceTest {

    @Autowired
    LookupAllService lookupAllService;

    @Test
    void search() {
        Pageable pageable= PageRequest.of(0, 3);
        String totalKeyword="제목";

        Page<LookupAllDto> page= lookupAllService.search(totalKeyword, pageable);
        log.info(page.getContent());
    }

    @Test
    void match() {
        Pageable pageable= PageRequest.of(0, 3);
        String totalKeyword="제목";

        Page<LookupAllDto> page= lookupAllService.search(totalKeyword, pageable);
        log.info(page.getContent());
    }

    @Test
    void term() {
        Pageable pageable= PageRequest.of(0, 3);
        String totalKeyword="제목";

        Page<LookupAllDto> page= lookupAllService.search(totalKeyword, pageable);
        log.info(page.getContent());
    }
}