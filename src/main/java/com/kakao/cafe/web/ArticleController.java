package com.kakao.cafe.web;

import com.kakao.cafe.dto.CreateArticleDto;
import com.kakao.cafe.dto.ShowArticleDto;
import com.kakao.cafe.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/qna")
public class ArticleController {

    private final ArticleService articleService;

    @Autowired
    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @GetMapping("/form")
    public String qnaForm() {
        return "qna/form";
    }

    @PostMapping("")
    public String qnaList(CreateArticleDto createArticleDto, Model model) {
        articleService.save(createArticleDto);
        List<ShowArticleDto> articleList = articleService.findAll();
        model.addAttribute("articleList", articleList);
        return "redirect:/";
    }

    @GetMapping("/{id}")
    public String qnaShow(Model model, @PathVariable String id) {
        ShowArticleDto article = articleService.findById(id);
        model.addAttribute("article", article);
        return "/qna/show";
    }
}
