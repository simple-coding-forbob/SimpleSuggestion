package com.simplecoding.simlesuggestion.es.search.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

@Document(indexName = "search-all")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Search {
    @Id
    private String id;
    private String type;
    private String name;
    private String loc;
    private String job;
}
