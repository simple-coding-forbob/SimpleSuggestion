package com.simplecoding.simlesuggestion.es.lookup.entity;

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
    private String title;
    private String content;
    private String question;
    private String answer;
}
