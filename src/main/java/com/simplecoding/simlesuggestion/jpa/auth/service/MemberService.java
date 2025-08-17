package com.simplecoding.simlesuggestion.jpa.auth.service;



import com.simplecoding.simlesuggestion.jpa.auth.dto.MemberDto;
import com.simplecoding.simlesuggestion.jpa.auth.entity.Member;
import com.simplecoding.simlesuggestion.jpa.auth.repository.MemberRepository;
import com.simplecoding.simlesuggestion.common.ErrorMsg;
import com.simplecoding.simlesuggestion.common.MapStruct;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final MapStruct mapStruct;
    private final PasswordEncoder passwordEncoder;
    private final ErrorMsg errorMsg;

//    저장 : 회원가입
    public void save(MemberDto memberDto) {
        // 이메일 있는지 확인
        if (memberRepository.existsById(memberDto.getEmail())) {
            throw new RuntimeException(errorMsg.getMessage("errors.register"));
        }
        String encodedPassword = passwordEncoder.encode(memberDto.getPassword());
        // 비밀번호 암호화
        memberDto.setPassword(encodedPassword);
        Member member=mapStruct.toEntity(memberDto);
        memberRepository.save(member);
    }
}
