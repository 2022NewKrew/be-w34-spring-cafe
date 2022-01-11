package com.kakao.cafe.module.controller;

import com.kakao.cafe.module.service.HomeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

import static com.kakao.cafe.module.model.dto.ArticleDtos.*;

@Controller
@RequiredArgsConstructor
public class HomeController {

    private final HomeService homeService;

    @GetMapping("")
    public String articleList(Model model){
        List<ArticleListDto> articleList = homeService.articleList();
        model.addAttribute("articleList", articleList);
        return "index";
    }
}
