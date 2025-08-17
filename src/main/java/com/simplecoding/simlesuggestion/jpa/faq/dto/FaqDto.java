package com.simplecoding.simlesuggestion.jpa.faq.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class FaqDto {
    private Long fno;           // 번호(기본키)
    private String title;      // 제목
    private String content;    // 내용
}
