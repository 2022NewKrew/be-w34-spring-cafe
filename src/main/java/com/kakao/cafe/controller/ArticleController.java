package com.kakao.cafe.controller;

import com.kakao.cafe.domain.dto.ArticleModifyDto;
import com.kakao.cafe.domain.model.Article;
import com.kakao.cafe.domain.dto.ArticleSaveDto;
import com.kakao.cafe.domain.model.User;
import com.kakao.cafe.exception.InvalidUserException;
import com.kakao.cafe.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.Objects;

@Controller
@RequestMapping("/article")
@RequiredArgsConstructor
public class ArticleController {
    private final ArticleService articleService;

    @GetMapping("/{id}")
    public String findArticleById(@PathVariable String id, Model model){
        Article article = articleService.findArticleById(id);

        model.addAttribute("article", article);
        return "article/view";
    }

    @GetMapping("/post")
    public String articlePostView(){
        return "article/post";
    }

    @PostMapping("/post")
    public String postArticle(@Valid ArticleSaveDto articleSaveDTO, HttpSession session) {
        User sessionedUser = (User) session.getAttribute("sessionedUser");

        articleSaveDTO.setUserId(sessionedUser.getUserId());
        articleService.save(articleSaveDTO);

        return "redirect:/";
    }

    @GetMapping("/modify/{id}")
    public String getModifyArticleView(@PathVariable String id, Model model, HttpSession session){

        User user = (User) session.getAttribute("sessionedUser");
        Article article = articleService.findArticleById(id);

        if(!user.isSameUser(article.getUserId())){
            throw new InvalidUserException();
        }

        model.addAttribute("article", article);
        return "article/modify";
    }

    @PutMapping("/modify/{id}")
    public String modifyArticle(@Valid ArticleModifyDto articleModifyDto, @PathVariable String id, HttpSession session){
        User user = (User) session.getAttribute("sessionedUser");
        Article article = articleService.findArticleById(id);

        if(!user.isSameUser(article.getUserId())){
            throw new InvalidUserException();
        }

        articleModifyDto.setId(Integer.parseInt(id.trim()));

        articleService.modifyArticle(articleModifyDto);
        return "redirect:/article/" + id;
    }

    @DeleteMapping("/delete/{id}")
    public String deleteArticle(@PathVariable String id, HttpSession session){
        User user = (User) session.getAttribute("sessionedUser");
        Article article = articleService.findArticleById(id);

        if(!user.isSameUser(article.getUserId())){
            throw new InvalidUserException();
        }

        articleService.deleteArticle(id);
        return "redirect:/";
    }
}
