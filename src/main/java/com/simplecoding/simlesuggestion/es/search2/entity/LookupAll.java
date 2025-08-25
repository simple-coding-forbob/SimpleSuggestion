package com.simplecoding.simlesuggestion.es.search2.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

@Document(indexName = "lookup-all")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class LookupAll {
    @Id
    private String id;
    private String type;
    private String name;      // 통합필드
    private String loc;
    private String job;
}
