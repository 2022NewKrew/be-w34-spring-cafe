package com.kakao.cafe.Controller;

import com.kakao.cafe.DTO.ArticleDTO;
import com.kakao.cafe.DTO.ArticleList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ArticleController {
    Logger logger = LoggerFactory.getLogger(ArticleController.class);
    private ArticleList articleList = new ArticleList();

    @PostMapping("/post")
    public String post(ArticleDTO articleDTO){
        articleList.add(articleDTO);
        logger.info("{}(title) was posted by {}(author)", articleDTO.getTitle(), articleDTO.getAuthor());
        return "redirect:/";
    }

    @GetMapping("/")
    public String home(Model model){
        logger.info("GET /");
        logger.info("{}", articleList);

        model.addAttribute("ArticleList", articleList);
        return "/index.html";
    }
}
