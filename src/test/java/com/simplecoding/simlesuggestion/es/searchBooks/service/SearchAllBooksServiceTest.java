package com.simplecoding.simlesuggestion.es.searchBooks.service;

import com.simplecoding.simlesuggestion.es.search.dto.SearchAllDto;
import com.simplecoding.simlesuggestion.es.search.service.SearchAllService;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import static org.junit.jupiter.api.Assertions.*;

@Log4j2
@SpringBootTest
class SearchAllBooksServiceTest {

    @Autowired
    SearchAllService searchAllService;

    @Test
    void searchBooks() {
        Pageable pageable= PageRequest.of(0, 10);
        Page<SearchAllDto> page= searchAllService.searchBooks("노동벏", pageable);
        log.info("result:{}",page.getContent());
    }
}