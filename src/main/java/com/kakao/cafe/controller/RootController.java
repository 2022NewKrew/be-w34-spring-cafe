package com.kakao.cafe.controller;

import com.kakao.cafe.model.ArticleDTO;
import com.kakao.cafe.model.data_storage.ArticleTable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/")
public class RootController {
    Logger logger = LoggerFactory.getLogger(RootController.class);

    @GetMapping
    String root(Model model){
        List<ArticleDTO> articles = ArticleTable.allArticleInfo();

        logger.info(articles.toString());
        model.addAttribute("articles", articles);

        return "/index";
    }
}
