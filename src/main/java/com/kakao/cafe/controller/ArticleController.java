package com.kakao.cafe.controller;

import com.kakao.cafe.dto.ArticleCreateDto;
import com.kakao.cafe.dto.ArticleShowDto;
import com.kakao.cafe.service.ArticleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.stream.Collectors;


@Slf4j
@Controller
public class ArticleController {

    private final ArticleService articleService;

    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @PostMapping("/questions/create")
    public String addArticle(@ModelAttribute @Validated ArticleCreateDto articleCreateDto
            , BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("errors", bindingResult
                    .getFieldErrors()
                    .stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .collect(Collectors.toList()));
            return "qna/upload_failed";
        }
        articleService.save(articleCreateDto);
        return "redirect:/";
    }

    @GetMapping("/")
    public String showArticles(Model model) {
        model.addAttribute("articles", articleService.findAll());
        return "index";
    }

    @GetMapping("questions/{id}")
    public String getArticle(@PathVariable Integer id, Model model) {
        ArticleShowDto article = articleService.findOne(id);
        model.addAttribute("article", article);
        return "qna/show";
    }

}
