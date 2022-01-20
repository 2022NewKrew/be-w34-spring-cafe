package com.kakao.cafe.controller;

import java.util.List;
import java.util.Map;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kakao.cafe.article.service.ArticleService;
import com.kakao.cafe.article.service.dto.AllArticlesListServiceResponse;
import com.kakao.cafe.controller.viewdto.ArticleControllerResponseMapper;

@Controller
@RequiredArgsConstructor
@RequestMapping("/")
@Slf4j
public class HomeController {

    private final ArticleService articleService;

    @GetMapping("/")
    public String getLandingPage(Model model) {
        log.info("GET /");
        AllArticlesListServiceResponse articleDTO = articleService.getAllArticleViewDTO(0L);
        List<Map<String, Object>> result = ArticleControllerResponseMapper.getArticleListResponse(articleDTO);
        model.addAttribute("articles", result);
        return "index";
    }

}
