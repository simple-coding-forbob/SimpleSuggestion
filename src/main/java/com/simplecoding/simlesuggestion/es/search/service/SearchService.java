package com.simplecoding.simlesuggestion.es.search.service;

import com.simplecoding.simlesuggestion.common.MapStruct;
import com.simplecoding.simlesuggestion.es.search.dto.SearchDto;
import com.simplecoding.simlesuggestion.es.search.entity.Search;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.client.elc.NativeQuery;
import org.springframework.data.elasticsearch.client.elc.NativeQueryBuilder;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SearchService {
    private final ElasticsearchOperations elasticsearchOperations;
    private final MapStruct mapStruct;

    public Page<SearchDto> search(String keyword, Pageable pageable) {
        NativeQuery query = new NativeQueryBuilder()
                .withQuery(q -> q.multiMatch(m -> m
                        .query(keyword)
                        .fields("name", "loc", "job")
                ))
                .withPageable(pageable)  // 페이징
                .build();

//        콘텐츠 기반 추천 기능: es 에서 바로 제공
//        NativeQuery query = new NativeQueryBuilder()
//                .withQuery(q -> q.moreLikeThis(m -> m
//                        .fields("name", "loc", "job")
//                        .like(l -> l.text("홍길동"))
//                        .minTermFreq(1)
//                        .minDocFreq(1)
//                ))
//                .withPageable(pageable)
//                .build();

        SearchHits<Search> hits = elasticsearchOperations.search(query, Search.class);
        List<SearchDto> content =  hits.getSearchHits().stream()
                .map(hit -> mapStruct.toDto(hit.getContent()))
                .collect(Collectors.toList());
        return new PageImpl<>(content, pageable, hits.getTotalHits());
    }
}

