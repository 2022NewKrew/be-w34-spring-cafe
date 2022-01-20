package com.kakao.cafe.controller;

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
    public String postArticle(@Valid ArticleSaveDto articleSaveDTO, HttpSession session) throws Exception {

        Object value = session.getAttribute("sessionedUser");
        if(Objects.isNull(value)){
            throw new InvalidUserException();
        }
        User user = (User) value;
        articleSaveDTO.setUserId(user.getUserId());
        articleService.save(articleSaveDTO);
        return "redirect:/";
    }
}
