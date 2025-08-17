package com.simplecoding.simlesuggestion.es.filedbsuggested.controller;

import com.simplecoding.simlesuggestion.es.filedbsuggested.dto.FileDbSuggestedDto;
import com.simplecoding.simlesuggestion.es.filedbsuggested.service.FileDbSuggestedService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class FileDbSuggestedController {

    private final FileDbSuggestedService fileDbSuggestedService;

//    상세조회
    @GetMapping("/mypage")
    public String findById(Model model) {
        FileDbSuggestedDto fileDbSuggestedDto= fileDbSuggestedService.findById();
        //		서비스의 상세조회
        model.addAttribute("fileDbSuggested", fileDbSuggestedDto);
        return "mypage";
    }
}
