package com.simplecoding.simlesuggestion.es.search.service;

import com.simplecoding.simlesuggestion.es.search.dto.SearchAllDto;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

@Log4j2
@SpringBootTest
class SearchServiceTest {

    @Autowired
    SearchAllService searchService;

    @Test
    void search() {
        Pageable pageable= PageRequest.of(0, 3);
        String totalKeyword="Scott";

        Page<SearchAllDto> page= searchService.search(totalKeyword, pageable);
        log.info(page.getContent());
    }

    @Test
    void match() {
        Pageable pageable= PageRequest.of(0, 3);
        String totalKeyword="sales";

        Page<SearchAllDto> page= searchService.match(totalKeyword, pageable);
        log.info(page.getContent());
    }

    @Test
    void term() {
        Pageable pageable= PageRequest.of(0, 3);
        String totalKeyword="MANAGER";

        Page<SearchAllDto> page= searchService.term(totalKeyword, pageable);
        log.info(page.getContent());
    }
}