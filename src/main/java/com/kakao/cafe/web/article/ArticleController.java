package com.kakao.cafe.web.article;

import com.kakao.cafe.domain.article.ArticleRegistrationService;
import com.kakao.cafe.infra.repository.article.ArticleRepository;
import com.kakao.cafe.web.article.form.ArticleRegistrationForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
public class ArticleController {

    private final ArticleRegistrationService articleRegistrationService;
    private final ArticleRepository articleRepository;

    @Autowired
    public ArticleController(ArticleRegistrationService articleRegistrationService, ArticleRepository articleRepository) {
        this.articleRegistrationService = articleRegistrationService;
        this.articleRepository = articleRepository;
    }

    @GetMapping("/qna/form")
    public String qnaForm(){
        return "qna/form";
    }

    @PostMapping("/questions")
    public String createQuestion(@Valid @ModelAttribute ArticleRegistrationForm articleRegistrationForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()){
            return "redirect:/qna/form";
        }
        articleRegistrationService.registerArticle(articleRegistrationForm);
        return "redirect:/";
    }

    @GetMapping("/")
    public String showArticleList(Model model) {
        model.addAttribute("articles", articleRepository.getArticleInventoryInfoList());
        return "index";
    }

    @GetMapping("articles/{articleId}")
    public String showArticle(@PathVariable("articleId") Long articleId, Model model) {
        model.addAttribute("article", articleRepository.findArticlePostInfo(articleId));
        return "qna/show";
    }
}
