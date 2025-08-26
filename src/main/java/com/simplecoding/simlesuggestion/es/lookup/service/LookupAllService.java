package com.simplecoding.simlesuggestion.es.lookup.service;

import com.simplecoding.simlesuggestion.common.MapStruct;
import com.simplecoding.simlesuggestion.es.lookup.dto.LookupAllDto;
import com.simplecoding.simlesuggestion.es.lookup.entity.LookupAll;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.client.elc.NativeQueryBuilder;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LookupAllService {
    private final ElasticsearchOperations elasticsearchOperations;
    private final MapStruct mapStruct;

    //   1) 퀴즈) 통합 검색하기
    public Page<LookupAllDto> search(String keyword, Pageable pageable) {
        Query query = new NativeQueryBuilder()
                .withQuery(q -> q.multiMatch(m -> m
                        .fields("title", "content", "question", "answer")
                        .query(keyword)
                ))
                .withPageable(pageable)  // 페이징
                .build();

        SearchHits<LookupAll> hits = elasticsearchOperations.search(query, LookupAll.class);      // 총 개수
        List<LookupAllDto> content =  hits.getSearchHits().stream()
                .map(hit -> mapStruct.toDto(hit.getContent()))
                .collect(Collectors.toList());                                              // 내용
        return new PageImpl<>(content, pageable, hits.getTotalHits());
    }

    //  2) 퀴즈) match 검색: 1개짜리 검색
    public Page<LookupAllDto> match(String keyword, Pageable pageable) {
        Query query = new NativeQueryBuilder()
                .withQuery(q -> q.match(m -> m
                        .field("title")
                        .query(keyword)
                ))
                .withPageable(pageable)  // 페이징
                .build();

        SearchHits<LookupAll> hits = elasticsearchOperations.search(query, LookupAll.class);     // 총 개수
        List<LookupAllDto> content =  hits.getSearchHits().stream()
                .map(hit -> mapStruct.toDto(hit.getContent()))
                .collect(Collectors.toList());                                              // 내용
        return new PageImpl<>(content, pageable, hits.getTotalHits());
    }

    //  3) 퀴즈) term 검색: 1개짜리 검색
    public Page<LookupAllDto> term(String keyword, Pageable pageable) {
        Query query = new NativeQueryBuilder()
                .withQuery(q -> q.term(m -> m
                        .field("content.keyword")
                        .value(keyword)                                                   // 정확히 일치
                ))
                .withPageable(pageable)  // 페이징
                .build();

        SearchHits<LookupAll> hits = elasticsearchOperations.search(query, LookupAll.class);     // 총 개수
        List<LookupAllDto> content =  hits.getSearchHits().stream()
                .map(hit -> mapStruct.toDto(hit.getContent()))
                .collect(Collectors.toList());                                              // 내용
        return new PageImpl<>(content, pageable, hits.getTotalHits());
    }   
}
