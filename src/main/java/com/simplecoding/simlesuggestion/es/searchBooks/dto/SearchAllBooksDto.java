package com.simplecoding.simlesuggestion.es.searchBooks.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class SearchAllBooksDto {
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
