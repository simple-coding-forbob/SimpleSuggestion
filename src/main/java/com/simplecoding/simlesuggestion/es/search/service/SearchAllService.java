package com.simplecoding.simlesuggestion.es.search.service;

import com.simplecoding.simlesuggestion.common.MapStruct;
import com.simplecoding.simlesuggestion.es.search.dto.SearchAllDto;
import com.simplecoding.simlesuggestion.es.search.entity.SearchAll;
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
public class SearchAllService {
    private final ElasticsearchOperations elasticsearchOperations;
    private final MapStruct mapStruct;

//   1) 예제) 통합 검색하기
    public Page<SearchAllDto> search(String keyword, Pageable pageable) {
        Query query = new NativeQueryBuilder()
                .withQuery(q -> q.multiMatch(m -> m
                        .fields("dname", "loc", "ename", "job")
                        .query(keyword)
                ))
                .withPageable(pageable)  // 페이징
                .build();

        SearchHits<SearchAll> hits = elasticsearchOperations.search(query, SearchAll.class);      // 총 개수
        List<SearchAllDto> content =  hits.getSearchHits().stream()
                .map(hit -> mapStruct.toDto(hit.getContent()))
                .collect(Collectors.toList());                                              // 내용
        return new PageImpl<>(content, pageable, hits.getTotalHits());
    }

//  2) 예제) match 검색: 1개짜리 검색
    public Page<SearchAllDto> match(String keyword, Pageable pageable) {
        Query query = new NativeQueryBuilder()
                .withQuery(q -> q.match(m -> m
                        .field("name")
                        .query(keyword)
                ))
                .withPageable(pageable)  // 페이징
                .build();

        SearchHits<SearchAll> hits = elasticsearchOperations.search(query, SearchAll.class);      // 총 개수
        List<SearchAllDto> content =  hits.getSearchHits().stream()
                .map(hit -> mapStruct.toDto(hit.getContent()))
                .collect(Collectors.toList());                                              // 내용
        return new PageImpl<>(content, pageable, hits.getTotalHits());
    }

    //  3) 예제) term 검색: 1개짜리 검색
    public Page<SearchAllDto> term(String keyword, Pageable pageable) {
        Query query = new NativeQueryBuilder()
                .withQuery(q -> q.term(m -> m
                        .field("job.keyword")
                        .value(keyword)                                                   // 정확히 일치
                ))
                .withPageable(pageable)  // 페이징
                .build();

        SearchHits<SearchAll> hits = elasticsearchOperations.search(query, SearchAll.class);      // 총 개수
        List<SearchAllDto> content =  hits.getSearchHits().stream()
                .map(hit -> mapStruct.toDto(hit.getContent()))
                .collect(Collectors.toList());                                              // 내용
        return new PageImpl<>(content, pageable, hits.getTotalHits());
    }

}

