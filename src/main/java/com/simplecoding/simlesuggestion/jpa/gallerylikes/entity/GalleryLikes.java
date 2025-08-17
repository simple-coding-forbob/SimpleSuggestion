package com.simplecoding.simlesuggestion.jpa.gallerylikes.entity;

import com.simplecoding.simlesuggestion.jpa.auth.entity.Member;
import com.simplecoding.simlesuggestion.common.BaseTimeEntity;
import com.simplecoding.simlesuggestion.jpa.gallery.entity.Gallery;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "TB_GALLERY_LIKES")
@SequenceGenerator(
        name = "SQ_GALLERY_LIKES_JPA",
        sequenceName = "SQ_GALLERY_LIKES",
        allocationSize = 1
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode(of = "id", callSuper = false)
public class GalleryLikes extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE
            , generator = "SQ_GALLERY_LIKES_JPA"
    )
    private Long id;        // 기본키 : 자바생성
    private Long likeCount; // 좋아요 수

    // 단방향 조인 (GalleryLikes -> Member)
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "email")          // DB FK 컬럼명 작성
    private Member member;               // 부모 엔티티 클래스명

    // 단방향 조인 (GalleryLikes -> Gallery)
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "uuid")           // DB FK 컬럼명 작성
    private Gallery gallery;               // 부모 엔티티 클래스명
}
