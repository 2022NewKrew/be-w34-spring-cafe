package com.kakao.cafe.article.controller;

import com.kakao.cafe.article.dto.ArticleRequestDTO;
import com.kakao.cafe.article.dto.ArticleResponseDTO;
import com.kakao.cafe.article.service.ArticleService;
import com.kakao.cafe.member.dto.MemberResponseDTO;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class ArticleController {

    private final ArticleService articleService;
    private final Logger logger = LoggerFactory.getLogger(ArticleController.class);

    @GetMapping("/")
    public String getArticles(Model model) {
        List<ArticleResponseDTO> articles = articleService.findAll();

        model.addAttribute("totalSize", articles.size());
        model.addAttribute("articles", articles);
        return "index";
    }

    @GetMapping("/articleForm")
    public String getArticleForm() {
        return "articles/form";
    }

    @PostMapping("/articles")
    public String postArticles(@Valid ArticleRequestDTO articleRequestDTO, HttpSession session) {
        logger.info(articleRequestDTO.toString());
        MemberResponseDTO loginMemberDTO = (MemberResponseDTO) session.getAttribute("loginMemberDTO");
        articleService.posting(articleRequestDTO, loginMemberDTO.getId());
        return "redirect:/";
    }

    @GetMapping("/articles/{id}")
    public String getArticle(@PathVariable Long id, Model model) {
        model.addAttribute("article", articleService.findOne(id));
        return "articles/show";
    }

    @PutMapping("/articles/{id}")
    public String putArticles(@PathVariable Long id, @Valid ArticleRequestDTO articleRequestDTO, HttpSession session) {
        logger.info(articleRequestDTO.toString());
        MemberResponseDTO loginMemberDTO = (MemberResponseDTO) session.getAttribute("loginMemberDTO");
        articleService.update(id, loginMemberDTO.getId(), articleRequestDTO);
        return "redirect:/";
    }

    @DeleteMapping("/articles/{id}")
    public String deleteArticles(@PathVariable Long id, HttpSession session) {
        MemberResponseDTO loginMemberDTO = (MemberResponseDTO) session.getAttribute("loginMemberDTO");
        articleService.delete(id, loginMemberDTO.getId());
        return "redirect:/";
    }

    @GetMapping("/articles/{id}/edit")
    public String editArticle(@PathVariable Long id, Model model) {
        model.addAttribute("article", articleService.findOne(id));
        return "articles/editForm";
    }
}
