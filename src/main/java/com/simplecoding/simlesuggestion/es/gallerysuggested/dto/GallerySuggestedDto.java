package com.simplecoding.simlesuggestion.es.gallerysuggested.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class GallerySuggestedDto {
    private String email;
    private String suggested;
    private LocalDateTime insertTime;
    private LocalDateTime updateTime;
}
