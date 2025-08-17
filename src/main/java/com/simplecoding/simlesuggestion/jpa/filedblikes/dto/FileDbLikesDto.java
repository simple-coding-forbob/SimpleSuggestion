package com.simplecoding.simlesuggestion.jpa.filedblikes.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class FileDbLikesDto {
    private Long id;          // 기본키 : 자바생성
    private String email;     // 참조키, TB_MEMBER 의 email
    private String uuid;      // 참조키, TB_FILE_DB 의 uuid
    private Long likeCount;   // 좋아요 수
}
