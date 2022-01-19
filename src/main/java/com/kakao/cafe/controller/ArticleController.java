package com.kakao.cafe.controller;

import com.kakao.cafe.dto.ArticleDto;
import com.kakao.cafe.dto.ArticleListDto;
import com.kakao.cafe.dto.ArticleRequestDto;
import com.kakao.cafe.entity.User;
import com.kakao.cafe.service.ArticleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class ArticleController {
    private final ArticleService articleService;
    private Logger logger = LoggerFactory.getLogger(UserController.class);

    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @PostMapping("/qna/create")
    public String createArticle(ArticleRequestDto articleRequestDto, HttpSession httpSession) {
        articleService.createArticle(articleRequestDto, httpSession);
        return "redirect:/";
    }

    @GetMapping("/")
    public String getArticleList(Model model) {
        List<ArticleListDto> articleList = articleService.getArticleList();
        model.addAttribute("articleList", articleList);
        return "index";
    }

    @GetMapping("/qna/form")
    public String createArticle(HttpSession httpSession){
        User sessionedUser = (User) httpSession.getAttribute("sessionedUser");
        if(sessionedUser == null)
            return "redirect:/user/login";
        return "qna/form";
    }

    @GetMapping("/articles/{index}")
    public String getArticleInfo(@PathVariable String index, Model model, HttpSession httpSession) {
        ArticleDto article = articleService.findById(index);
        model.addAttribute("article", article);

        boolean isUserEqualToWriter = articleService.isUpdatable(article, httpSession);
        model.addAttribute("isUpdatable", isUserEqualToWriter);

        return "qna/show";
    }

    @GetMapping("/articles/update/{index}")
    public String getArticleUpdateForm(@PathVariable String index, Model model, HttpSession httpSession) {
        ArticleDto article = articleService.findById(index);
        model.addAttribute("article", article);

        if(!articleService.isUpdatable(article, httpSession))
            return "qna/updateImpossible";

        return "qna/update";
    }

    @PutMapping("/qna/update/{index}")
    public String updateArticle(@PathVariable String index, ArticleRequestDto articleRequestDto, Model model) {
        ArticleDto article = articleService.updateById(index, articleRequestDto);
        model.addAttribute("article", article);
        return "redirect:/articles/{index}";
    }
}
