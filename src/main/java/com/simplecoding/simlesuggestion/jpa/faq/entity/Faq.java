package com.simplecoding.simlesuggestion.jpa.faq.entity;


import com.simplecoding.simlesuggestion.common.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "TB_FAQ")
@SequenceGenerator(
        name = "SQ_FAQ_JPA",
        sequenceName = "SQ_FAQ",
        allocationSize = 1
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode(of = "fno", callSuper = false)
public class Faq extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE
            , generator = "SQ_FAQ_JPA"
    )
    private Long fno;           // 번호(기본키)
    private String title;      // 제목
    private String content;    // 내용
}
