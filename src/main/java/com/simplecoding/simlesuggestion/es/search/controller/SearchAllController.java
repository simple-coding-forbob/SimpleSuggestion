package com.simplecoding.simlesuggestion.es.search.controller;

import com.simplecoding.simlesuggestion.es.search.dto.SearchAllDto;
import com.simplecoding.simlesuggestion.es.search.service.SearchAllService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class SearchAllController {

    private final SearchAllService searchService;

    @GetMapping("/search")
    public String search(@RequestParam(defaultValue = "") String totalKeyword,
                         @PageableDefault(page = 0, size = 3) Pageable pageable,
                         Model model) {
        Page<SearchAllDto> pages= searchService.search(totalKeyword, pageable);
        model.addAttribute("searches", pages.getContent());
        model.addAttribute("pages", pages);
        return "search_all";
    }
}
