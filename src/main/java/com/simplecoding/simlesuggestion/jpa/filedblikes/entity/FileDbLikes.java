package com.simplecoding.simlesuggestion.jpa.filedblikes.entity;

import com.simplecoding.simlesuggestion.jpa.auth.entity.Member;
import com.simplecoding.simlesuggestion.common.BaseTimeEntity;
import com.simplecoding.simlesuggestion.jpa.filedb.entity.FileDb;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "TB_FILE_DB_LIKES")
@SequenceGenerator(
        name = "SQ_FILE_DB_LIKES_JPA",
        sequenceName = "SQ_FILE_DB_LIKES",
        allocationSize = 1
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode(of = "id", callSuper = false)
public class FileDbLikes extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE
            , generator = "SQ_FILE_DB_LIKES_JPA"
    )
    private Long id;        // 기본키
    private Long likeCount; // 좋아요 수

    // 단방향 조인 (FileDbLikes -> Member)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "email")          // DB FK 컬럼명 작성
    private Member member;               // 부모 엔티티 클래스명

    // 단방향 조인 (FileDbLikes -> FileDb)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "uuid")           // DB FK 컬럼명 작성
    private FileDb fileDb;               // 부모 엔티티 클래스명
}
