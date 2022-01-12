package com.kakao.cafe.controller;

import com.kakao.cafe.dto.ArticleDto;
import com.kakao.cafe.dto.UserDto;
import com.kakao.cafe.service.ArticleService;
import com.kakao.cafe.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ArticleController {
    Logger logger = LoggerFactory.getLogger(ArticleController.class);

    private final ArticleService articleService = new ArticleService();

    @PostMapping("/posts")
    public String addPost(@ModelAttribute ArticleDto articleDto) {
        articleDto.setAuthor("Seonghun");
        logger.info("POST /posts {}", articleDto);
        articleService.addPost(articleDto);
        return "redirect:/";
    }

    @GetMapping("/")
    public String findAll(Model model) {
        model.addAttribute("posts", articleService.findAll());
        return "index";
    }

    @GetMapping("/articles/{id}")
    public String findById(@PathVariable int id, Model model) {
        model.addAttribute("post", articleService.findById(id));
        return "post/show";
    }

//    @GetMapping("/users/{id}")
//    public String findById(@PathVariable int id , Model model) {
//        logger.info("GET /users/{}", id);
//        model.addAttribute("user",userService.findById(id));
//        return "user/profile";
//    }
}
