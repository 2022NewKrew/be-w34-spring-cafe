package com.kakao.cafe.web.controller;

import com.kakao.cafe.service.ArticleService;
import com.kakao.cafe.web.dto.ArticleRequest;
import com.kakao.cafe.web.dto.ArticleResponse;
import com.kakao.cafe.web.dto.UserRequest;
import com.kakao.cafe.web.dto.UserResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Optional;

@Slf4j
@Controller
public class ArticleController {
    private final ArticleService articleService;

    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @PostMapping(value = "/article/create")
    public String postCreateArticle(String title, String content, HttpSession session) {
        UserResponse userResponse = (UserResponse) session.getAttribute("sessionUser");
        ArticleRequest articleRequest = ArticleRequest.newInstance(title, content, userResponse.getUserId());
        articleService.createArticle(articleRequest);
        return "redirect:/index";
    }

    @GetMapping(value = "/index")
    public String getArticleList(Model model) {
        log.info("articleList:{}", articleService.getArticleList());
        model.addAttribute("articleListSize", articleService.getArticleListSize());
        model.addAttribute("articleList", articleService.getArticleList());
        return "/index";
    }

    @GetMapping(value = "/articles/{index}")
    public String getArticleShow(@PathVariable int index, Model model,HttpSession session) {
        Optional<UserRequest> userRequest = (Optional<UserRequest>) session.getAttribute("sessionUser");
        ArticleResponse articleResponse = articleService.getArticleByIndex(index);
        model.addAttribute("article", articleResponse);
        return "/article/show";
    }

    @GetMapping(value = "/article/{index}/update")
    public String getArticleUpdateForm(@PathVariable int index, Model model) {
        ArticleResponse articleResponse = articleService.getArticleByIndex(index);
        model.addAttribute("article", articleResponse);
        return "/article/updateForm";
    }

    @PutMapping(value= "/article/{index}/edit")
    public String putArticleUpdate(@PathVariable int index,String title,String content){
        articleService.updateArticle(index,title,content);
        return "redirect:/articles/{index}";
    }

//    @ExceptionHandler(IllegalArticleUpdateException e)

}
