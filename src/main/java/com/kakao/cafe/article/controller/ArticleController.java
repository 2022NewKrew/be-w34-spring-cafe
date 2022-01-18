package com.kakao.cafe.article.controller;

import com.kakao.cafe.article.dto.request.ArticleReqDto;
import com.kakao.cafe.article.dto.response.ArticleDetailResDto;
import com.kakao.cafe.article.service.ArticleService;
import com.kakao.cafe.exception.SessionUserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Controller
@RequiredArgsConstructor
@RequestMapping("/articles")
public class ArticleController {
    private final ArticleService articleService;

    @PostMapping
    public String saveArticle(@ModelAttribute ArticleReqDto articleReqDto) {
        articleService.saveArticle(articleReqDto);
        return "redirect:/";
    }

    @GetMapping("/{id}")
    public String showArticle(@PathVariable Long id, Model model, HttpSession session) {
        checkSessionUser(session);

        ArticleDetailResDto article = articleService.getArticle(id);
        model.addAttribute("article", article);

        return "/qna/show";
    }

    @GetMapping("/form")
    public String showRegisterForm(HttpSession session) {
        checkSessionUser(session);

        return "/qna/form";
    }

    @GetMapping("/{id}/form")
    public String showUpdateForm(@PathVariable Long id, Model model, HttpSession session) {
        ArticleDetailResDto article = articleService.getArticle(id);
        checkWriterSameAsSessionUser(session, article.getWriter());
        model.addAttribute("article", article);
        return "/qna/updateForm";
    }

    @PutMapping
    public String updateArticle(@ModelAttribute ArticleReqDto articleReqDto) {
        articleService.update(articleReqDto);
        return "redirect:/articles/" + articleReqDto.getId();
    }

    @DeleteMapping
    public String deleteArticle(@ModelAttribute ArticleReqDto articleReqDto, HttpSession session) {
        checkWriterSameAsSessionUser(session, articleReqDto.getWriter());
        articleService.delete(articleReqDto.getId());
        return "redirect:/";
    }

    private void checkWriterSameAsSessionUser(HttpSession session, String writer) {
        if (!session.getAttribute("sessionUserId").equals(writer)) {
            throw new IllegalArgumentException("작성자만 글을 수정할 수 있습니다.");
        }
    }

    private void checkSessionUser(HttpSession session) {
        if (session.getAttribute("sessionUserId") == null) {
            throw new SessionUserNotFoundException();
        }
    }
}
