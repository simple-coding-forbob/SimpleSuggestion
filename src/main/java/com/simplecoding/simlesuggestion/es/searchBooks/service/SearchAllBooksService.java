package com.simplecoding.simlesuggestion.es.searchBooks.service;

import com.simplecoding.simlesuggestion.common.MapStruct;
import com.simplecoding.simlesuggestion.es.searchBooks.dto.SearchAllBooksDto;
import com.simplecoding.simlesuggestion.es.searchBooks.entity.SearchAllBooks;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.StringQuery;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SearchAllBooksService {
    private final ElasticsearchOperations elasticsearchOperations;
    private final MapStruct mapStruct;

    public Page<SearchAllBooksDto> searchBooks(String keyword,  Pageable pageable) {

        // 1. Elasticsearch JSON 쿼리를 문자열로 생성
        String queryJson = """
        {
          "from": %d,
          "size": %d,
          "track_total_hits": true,
          "query": {
            "bool": {
              "should": [
                {
                  "multi_match": {
                    "query": "%s",
                    "type": "bool_prefix",
                    "fields": [
                      "title.autocomplete^3",
                      "author_name.autocomplete^2",
                      "publisher_name.autocomplete",
                      "category_name.autocomplete"
                    ]
                  }
                },
                {
                  "multi_match": {
                    "query": "%s",
                    "fields": [
                      "title^5",
                      "author_name^3",
                      "publisher_name^2",
                      "category_name"
                    ],
                    "operator": "and",
                    "fuzziness": "AUTO"
                  }
                }
              ],
              "minimum_should_match": 1
            }
          },
          "highlight": {
            "pre_tags": ["<em>"],
            "post_tags": ["</em>"],
            "fields": {
              "title": {},
              "author_name": {},
              "publisher_name": {},
              "category_name": {}
            }
          }
        }
        """.formatted(pageable.getOffset(), pageable.getPageSize(), keyword, keyword);

        StringQuery query = new StringQuery(queryJson);

        SearchHits<SearchAllBooks> hits = elasticsearchOperations.search(query, SearchAllBooks.class);      // 총 개수
        List<SearchAllBooksDto> content =  hits.getSearchHits().stream()
                .map(hit -> mapStruct.toDto(hit.getContent()))
                .collect(Collectors.toList());                                              // 내용
        return new PageImpl<>(content, pageable, hits.getTotalHits());
    }

}

