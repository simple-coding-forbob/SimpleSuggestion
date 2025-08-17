package com.simplecoding.simlesuggestion.es.filedbsuggested.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.time.LocalDateTime;
import java.util.List;

@Document(indexName = "filedb-likes-suggested")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode(of = "email")
public class FileDbSuggested  {
    @Id
    private String email;
    @Field(type = FieldType.Keyword)
    private List<String> suggested;

    @Field(type = FieldType.Date)
    private LocalDateTime insertTime;
    @Field(type = FieldType.Date)
    private LocalDateTime updateTime;
}
