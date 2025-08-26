package com.simplecoding.simlesuggestion.es.lookup.dto;

import lombok.*;
import org.springframework.data.annotation.Id;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class LookupAllDto {
    private String id;
    private String type;
    private String title;
    private String content;
    private String question;
    private String answer;
}
