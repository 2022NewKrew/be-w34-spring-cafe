package com.kakao.cafe.app.controller;

import com.kakao.cafe.app.request.ArticleRequest;
import com.kakao.cafe.service.ArticleService;
import com.kakao.cafe.service.dto.ArticleDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
public class ArticleController {

    private final ArticleService service;

    @Autowired
    public ArticleController(ArticleService service) {
        this.service = service;
    }

    @PostMapping("/articles")
    public String write(
            @Valid @ModelAttribute ArticleRequest request,
            HttpSession session
    ) {
        Long ownerId = (Long) session.getAttribute("id");
        if (ownerId == null) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "not logged in");
        }
        service.create(ownerId, request.toDraftDto());
        return "redirect:/";
    }

    @GetMapping("/")
    public String list(Model model) {
        List<ArticleDto> articles = service.list();
        model.addAttribute("articles", articles);
        return "index";
    }

    @GetMapping("/articles/{id}")
    public String read(@PathVariable Long id, Model model) {
        Optional<ArticleDto> article = service.getById(id);
        if (article.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "article not found");
        }
        model.addAttribute("article", article);
        return "articles/item";
    }
}
