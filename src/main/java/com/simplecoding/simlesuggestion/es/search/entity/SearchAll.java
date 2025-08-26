package com.simplecoding.simlesuggestion.es.search.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

@Document(indexName = "search-all")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class SearchAll {
    @Id
    private String id;     // 문서 id
    private String type;   // emp / dept 구분
    private String dname;
    private String loc;
    private String ename;
    private String job;    // emp 전용
}
