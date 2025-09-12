package com.simplecoding.simlesuggestion.es.gallerysuggested.entity;

import org.springframework.data.annotation.Id;
import lombok.*;
import org.springframework.data.elasticsearch.annotations.Document;

import java.time.LocalDateTime;
import java.util.List;

@Document(indexName = "gallery-likes-suggested")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode(of = "email")
public class GallerySuggested {
    @Id
    private String email;
    private List<String> suggested;
    private LocalDateTime insertTime;
    private LocalDateTime updateTime;
}
