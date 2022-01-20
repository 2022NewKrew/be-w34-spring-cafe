package com.kakao.cafe.article.controller;

import com.kakao.cafe.article.dto.ArticleDto;
import com.kakao.cafe.article.dto.ArticleRegistrationDto;
import com.kakao.cafe.article.exception.AuthorNotMatchedException;
import com.kakao.cafe.article.model.Article;
import com.kakao.cafe.article.service.ArticleService;
import com.kakao.cafe.user.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@RequestMapping(value = "/")
public class ArticleController {
    private final ArticleService service;

    public ArticleController(ArticleService service) {
        this.service = service;
    }

    @GetMapping
    public String list(Model model) {
        List<ArticleDto> dtoList = service.fetchAll()
                .stream()
                .map(ArticleDto::new)
                .collect(Collectors.toUnmodifiableList());
        model.addAllAttributes(Map.of("articles", dtoList));
        model.addAttribute("articleCount", dtoList.size());
        return "index";
    }

    @PostMapping(value = "articles")
    public String register(ArticleRegistrationDto dto, HttpSession session) {
        User author = (User) session.getAttribute("user");
        service.create(dto, author);
        return "redirect:/";
    }

    @GetMapping(value = "articles/{id}")
    public String show(@PathVariable Long id, Model model) {
        ArticleDto articleDto = new ArticleDto(service.fetch(id));
        model.addAttribute("article", articleDto);
        return "post/show";
    }

    @PutMapping(value = "articles/{id}")
    public String update(@PathVariable Long id, ArticleRegistrationDto dto, HttpSession session) {
        User author = (User) session.getAttribute("user");
        Article fetch = service.fetch(id);
        if (!fetch.getAuthor().equals(author)) {
            throw new AuthorNotMatchedException("해당 게시글의 작성자만 수정할 수 있습니다.");
        }
        service.update(fetch, dto);
        return String.format("redirect:articles/%s", id);
    }
}
