package com.simplecoding.simlesuggestion.es.gallerysuggested.dto;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class GallerySuggestedDto {
    private String email;
    private List<String> suggested;
    private LocalDateTime insertTime;
    private LocalDateTime updateTime;
}
