package com.kakao.cafe.controller;

import com.kakao.cafe.dto.ArticleFormDTO;
import com.kakao.cafe.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/article")
@RequiredArgsConstructor
@Controller
public class ArticleController {
    private final ArticleService articleService;

    @GetMapping()
    public String articleList(Model model) {
        model.addAttribute("articles", articleService.findAllShowListDTO());
        return "article/list";
    }

    @GetMapping("/post")
    public String articlePostForm() {
        return "article/form";
    }

    @PostMapping("/post")
    public String articlePost(ArticleFormDTO articleFormDTO) {
        articleService.join(articleFormDTO);
        return "redirect:/article";
    }

    @GetMapping("/{key}")
    public String articleShow(@PathVariable("key") long key, Model model) {
        model.addAttribute("article", articleService.findByKeyForShow(key));
        return "article/show";
    }
}
