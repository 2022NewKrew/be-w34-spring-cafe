package com.kakao.cafe.controller;

import com.kakao.cafe.domain.Article;
import com.kakao.cafe.domain.ArticleDTO;
import com.kakao.cafe.repository.article.ArticleNoBdUseRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/questions")
public class ArticleController {
    Logger logger = LoggerFactory.getLogger(ArticleController.class);

    @GetMapping("form")
    public String form(){
        return "/qna/form";
    }

    @PostMapping("form")
    public String form(ArticleDTO articleDTO){
        Article article = null;

        try {
            article = new Article(articleDTO);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if(!Objects.isNull(article)){
            ArticleNoBdUseRepository.saveUserInto(ArticleNoBdUseRepository.size(), article);
        }

        return "redirect:/";
    }

    @GetMapping("/detail/{index}")
    public String datail(@PathVariable("index") int index, Model model){
        Article article = ArticleNoBdUseRepository.lookUpUserInfo(index);

        logger.info(article.toString());

        List<ArticleDTO> comments = null;
        try {
            comments = article.getComments().stream()
                    .map(Article::toArticleDTO)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            comments = new ArrayList<>();
        }

        logger.info(comments.toString());

        ArticleDTO articleDTO = article.toArticleDTO();
        articleDTO.setCommentSize(article.commentSize());
        articleDTO.setComments(comments);
        logger.info(articleDTO.toString());
        model.addAttribute("article_detail", articleDTO);
        return "/qna/show";
    }
}
