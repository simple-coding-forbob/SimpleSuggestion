package com.simplecoding.simlesuggestion.es.lookup.controller;

import com.simplecoding.simlesuggestion.es.lookup.dto.LookupAllDto;
import com.simplecoding.simlesuggestion.es.lookup.service.LookupAllService;
import com.simplecoding.simlesuggestion.es.search.dto.SearchAllDto;
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
public class LookupAllController {
    private final LookupAllService lookupAllService;

    @GetMapping("/lookup")
    public String search(@RequestParam(defaultValue = "") String totalKeyword,
                         @PageableDefault(page = 0, size = 3) Pageable pageable,
                         Model model) {
        Page<LookupAllDto> pages= lookupAllService.search(totalKeyword, pageable);
        model.addAttribute("lookups", pages.getContent());
        model.addAttribute("pages", pages);
        return "lookup_all";
    }
}
