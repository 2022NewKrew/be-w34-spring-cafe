package com.kakao.cafe.controller;

import com.kakao.cafe.dto.ArticleDto;
import com.kakao.cafe.model.User;
import com.kakao.cafe.service.ArticleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.stream.Collectors;


@Slf4j
@Controller
public class ArticleController {

    private final ArticleService articleService;

    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @GetMapping("/questions/form")
    public String articleForm(HttpSession session, Model model){
        Object value = session.getAttribute("sessionUser");
        if (value != null) {
            User sessionUser = (User)value;
            model.addAttribute("user", sessionUser);
            return "qna/form";
        }
        return "redirect:/";
    }

    @PostMapping("/questions/create")
    public String addArticle(@ModelAttribute @Validated ArticleDto articleDto
            , BindingResult bindingResult, Model model, HttpSession session) {
        Object value = session.getAttribute("sessionUser");
        if (value != null) {
            User sessionUser = (User)value;
            articleDto.setUser(sessionUser);
            model.addAttribute("user", sessionUser);
        }
        if (bindingResult.hasErrors()) {
            model.addAttribute("errors", bindingResult
                    .getFieldErrors()
                    .stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .collect(Collectors.toList()));
            return "qna/upload_failed";
        }
        articleService.save(articleDto);
        return "redirect:/";
    }

    @GetMapping("/")
    public String showArticles(Model model) {
        model.addAttribute("articles", articleService.findAll());
        return "index";
    }

    @GetMapping("/questions/{id}")
    public String getArticle(@PathVariable Integer id, Model model, HttpSession session) {
        ArticleDto article = articleService.findOne(id);
        User sessionUser = (User)session.getAttribute("sessionUser");
        model.addAttribute("isWriter", sessionUser.getUserId().equals(article.getUser().getUserId()));
        model.addAttribute("article", article);
        return "qna/show";
    }

    @GetMapping("/questions/{id}/form")
    public String getArticle(@PathVariable Integer id, Model model) {
        ArticleDto article = articleService.findOne(id);
        model.addAttribute("article", article);
        return "qna/updateForm";
    }

    @PutMapping("/questions/{id}/update")
    public String updateArticle(@PathVariable Integer id, ArticleDto articleDto, HttpSession session){
        articleDto.setUser((User)session.getAttribute("sessionUser"));
        articleDto.setId(id);
        articleService.update(articleDto);
        return "redirect:/";
    }

    @DeleteMapping("/questions/{id}")
    public String deleteArticle(@PathVariable Integer id){
        articleService.delete(id);
        return "redirect:/";
    }

}
