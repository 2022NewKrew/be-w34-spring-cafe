package com.kakao.cafe.controller;

import com.kakao.cafe.dto.ArticleDto;
import com.kakao.cafe.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpSession;
import java.util.List;
@Controller
public class ArticleController {

    private final ArticleService service;

    @Autowired
    public ArticleController(ArticleService service) {
        this.service = service;
    }

    @PostMapping("/articles")
    public String write(
            @RequestParam(value="writer") String author,
            @RequestParam String title,
            @RequestParam(value="contents") String content,
            HttpSession session
    ) {
        Long ownerId = (Long) session.getAttribute("id");
        if (ownerId == null) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "not logged in");
        }
        service.create(ownerId, author, title, content);
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
        ArticleDto article = service.getById(id);
        model.addAttribute("article", article);
        return "article";
    }
}
