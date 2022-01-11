package com.kakao.cafe.article.web;

import com.kakao.cafe.article.service.ArticleService;
import com.kakao.cafe.article.web.dto.ArticleSaveDto;
import com.kakao.cafe.article.web.dto.ArticleShowDto;
import java.util.List;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@RequiredArgsConstructor
@Controller
public class ArticleController {

    private final ArticleService articleService;

    @GetMapping("/")
    public String articleList(Model model) {
        List<ArticleShowDto> articleShowDtoList = articleService.findAllArticle();
        model.addAttribute("articles", articleShowDtoList);
        return "qna/list";
    }

    @GetMapping("/articles/{index}")
    public String articleDetail(@PathVariable("index") Long index, Model model) {
        ArticleShowDto articleShowDto = articleService.findArticle(index);
        model.addAttribute("article", articleShowDto);
        return "qna/show";
    }

    @PostMapping("/questions")
    public String articleAdd(@Valid ArticleSaveDto articleSaveDto) {
        articleService.addArticle(articleSaveDto);
        return "redirect:/";
    }
}
