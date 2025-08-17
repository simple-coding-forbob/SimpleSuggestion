package com.simplecoding.simlesuggestion.jpa.auth.controller;


import com.simplecoding.simlesuggestion.jpa.auth.dto.MemberDto;
import com.simplecoding.simlesuggestion.jpa.auth.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Log4j2
@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

//    로그인 함수
    @GetMapping("/auth/login")
    public String login() {
        return "auth/login";
    }

    //	회원가입 페이지 열기
    @GetMapping("/auth/register")
    public String registerView() {
        return "auth/register";
    }

//    회원 가입 함수(insert)
    @PostMapping("/auth/register/addition")
    public String register(Model model, @ModelAttribute MemberDto memberDto) {
        memberService.save(memberDto);
        //		성공메세지 JSP 전달
        model.addAttribute("msg", "회원 가입을 성공했습니다.");
        return "auth/register";
    }

    //	회원가입(권한등록) 페이지 열기
    @GetMapping("/admin/register")
    public String registerAdminView() {
        return "auth/register_admin";
    }

    //    회원 가입 함수(insert)
    @PostMapping("/admin/register/addition")
    public String registerAdmin(Model model, @ModelAttribute MemberDto memberDto) {
        memberService.save(memberDto);
        //		성공메세지 JSP 전달
        model.addAttribute("msg", "회원 가입을 성공했습니다.");
        return "auth/register_admin";
    }
}
