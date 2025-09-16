package com.simplecoding.simlesuggestion.es.searchBooks.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

@Document(indexName = "search-all-books")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class SearchAllBooks {
    @Id
    private String id;     // 문서 id
    private String author_name;   // emp / dept 구분
    private String bookId;
    private String bookImgUrl;
    private String categoryName;
    private String discountedPrice;    // emp 전용
    private String hashtags;    // emp 전용
    private String price;    // emp 전용
    private String publisherName;    // emp 전용
    private String stock;    // emp 전용
    private String tags;    // emp 전용
    private String title;    // emp 전용
}
