package com.simplecoding.simlesuggestion.es.search.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class SearchDto {
    private String id;     // 문서 id
    private String type;   // emp / dept 구분
    private String name;   // 공통 필드
    private String loc;    // dept 전용
    private String job;    // emp 전용
}

