package com.kakao.cafe.controller;

import com.kakao.cafe.model.Article;
import com.kakao.cafe.model.ArticleDTO;
import com.kakao.cafe.model.data_storage.ArticleTable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Objects;

@Controller
@RequestMapping("/questions")
public class ArticleController {
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
            ArticleTable.saveUserInto(ArticleTable.size() + 1, article);
        }

        return "redirect:/";
    }

}
