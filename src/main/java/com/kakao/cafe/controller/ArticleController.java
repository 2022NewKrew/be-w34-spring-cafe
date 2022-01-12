package com.kakao.cafe.controller;

import com.kakao.cafe.dto.ArticleDTO;
import com.kakao.cafe.service.ArticleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@RequestMapping("/article")
@RequiredArgsConstructor
@Controller
@Slf4j
public class ArticleController {
    private final ArticleService articleService;

    @GetMapping()
    public String articleList(Model model) {
        model.addAttribute("articles", articleService.findAllDTO());
        return "article/list";
    }

    @GetMapping("/post")
    public String articlePostForm(Model model) {
        model.addAttribute("article", new ArticleDTO());
        return "article/form";
    }

    @PostMapping("/post")
    public String articlePost(ArticleDTO articleDTO) {
        articleService.join(articleDTO);
        return "redirect:/article";
    }

    @GetMapping("/{key}")
    public String articleShow(@PathVariable("key") Long key, Model model) {
        Optional<ArticleDTO> articleDTO = articleService.findByKeyDTO(key);
        if (articleDTO.isEmpty()) {
            log.info("article doesn't exist");
            return "redirect:/article";
        }
        model.addAttribute("article", articleDTO.get());
        return "article/show";
    }
}
