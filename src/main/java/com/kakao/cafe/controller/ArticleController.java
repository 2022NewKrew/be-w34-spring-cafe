package com.kakao.cafe.controller;

import com.kakao.cafe.domain.Article;
import com.kakao.cafe.domain.ArticleDTO;
import com.kakao.cafe.service.ArticleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
@RequestMapping("/questions")
public class ArticleController {
    Logger logger = LoggerFactory.getLogger(ArticleController.class);
    private final ArticleService articleService;

    @Autowired
    public ArticleController(ArticleService articleService){
        this.articleService = articleService;
    }

    @GetMapping("form")
    public String form(){
        return "/qna/form";
    }

    @PostMapping("form")
    public String form(ArticleDTO articleDTO){
        logger.info(articleDTO.toString());
        articleService.join(articleDTO);

        return "redirect:/";
    }

    @GetMapping("/detail/{index}")
    public String datail(@PathVariable("index") int index, Model model){
        Optional<Article> optArticle = articleService.findOne(index);

        if(optArticle.isEmpty()){
            logger.error("[ArticleController > datail] 등록되지 않은 게시글 Id로 접근했습니다.");
            return "redirect:/";
        }

        ArticleDTO articleDTO = new ArticleDTO(optArticle.get());

        model.addAttribute("article_detail", articleDTO);
        return "/qna/show";
    }
}
