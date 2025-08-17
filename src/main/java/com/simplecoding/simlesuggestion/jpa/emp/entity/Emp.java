package com.simplecoding.simlesuggestion.jpa.emp.entity;


import com.simplecoding.simlesuggestion.common.BaseTimeEntity;
import com.simplecoding.simlesuggestion.jpa.dept.entity.Dept;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Entity
@Table(name = "TB_EMP")
@SequenceGenerator(
        name = "SQ_EMP_JPA",
        sequenceName = "SQ_EMP",
        allocationSize = 1
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode(of = "eno", callSuper = false)
public class Emp extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE
            , generator = "SQ_EMP_JPA"
    )
    private Long eno;                // 사원번호(기본키, 시퀀스)
    private String ename;               // 사원명
    private String job;                 // 직위
    private Long manager;            // 관리자사원번호
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate hiredate;         // 입사일 (화면에서 위와 같이 전달됨, 기본값 형태이므로 생략가능)
    private Long salary;             // 급여
    private Long commission;         // 보너스(상여금)
    // 단방향 조인 (Emp -> Dept)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "dno")           // DB FK 컬럼명 작성
    private Dept dept;                  // 부모 엔티티 클래스명
}
