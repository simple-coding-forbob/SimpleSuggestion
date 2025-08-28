package com.simplecoding.simlesuggestion.es.filedbsuggested.controller;

import com.simplecoding.simlesuggestion.es.filedbsuggested.dto.FileDbSuggestedDto;
import com.simplecoding.simlesuggestion.es.filedbsuggested.service.FileDbSuggestedService;
import com.simplecoding.simlesuggestion.es.gallerysuggested.dto.GallerySuggestedDto;
import com.simplecoding.simlesuggestion.es.gallerysuggested.service.GallerySuggestedService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class FileDbSuggestedController {

    private final FileDbSuggestedService fileDbSuggestedService;
//    TODO: 퀴즈 : GallerySuggestedService 코딩
    private final GallerySuggestedService gallerySuggestedService;

//    상세조회
    @GetMapping("/mypage")
    public String findById(Model model) {
        FileDbSuggestedDto fileDbSuggestedDto= fileDbSuggestedService.findById();
//        TODO: 퀴즈 : GallerySuggestedDto
        GallerySuggestedDto gallerySuggestedDto= gallerySuggestedService.findById();
        //		서비스의 상세조회
        model.addAttribute("fileDbSuggested", fileDbSuggestedDto);
        model.addAttribute("gallerySuggested", gallerySuggestedDto);
        return "mypage";
    }
}
