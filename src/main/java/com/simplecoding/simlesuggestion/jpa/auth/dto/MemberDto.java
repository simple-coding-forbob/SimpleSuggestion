package com.simplecoding.simlesuggestion.jpa.auth.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class MemberDto {
    private String email;
    private String password;
    private String name;     // 유저명
    private String codeName; // 권한명 ( ROLE_USER, ROLE_ADMIN )
}
