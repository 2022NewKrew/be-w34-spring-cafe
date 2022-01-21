package com.kakao.cafe.controller;

import com.kakao.cafe.core.meta.SessionData;
import com.kakao.cafe.domain.article.ArticleService;
import com.kakao.cafe.domain.article.dto.ArticleCreateDto;
import com.kakao.cafe.domain.article.dto.ArticleTableRowDto;
import com.kakao.cafe.domain.article.dto.ArticleUpdateDto;
import com.kakao.cafe.domain.comment.CommentService;
import com.kakao.cafe.domain.comment.dto.CommentTableRowDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/articles")
@RequiredArgsConstructor
public class ArticleController {

    private final ArticleService articleService;

    private final CommentService commentService;

    @GetMapping("/{id}")
    public String showDetail(Model model,
                             @PathVariable("id") Long id) {
        final ArticleTableRowDto article = articleService.increaseViewCountAndRetrieveTableRowById(id);
        final List<CommentTableRowDto> comments = commentService.retrieveAllInArticle(id);
        model.addAttribute("article", article);
        model.addAttribute("comments", comments);
        return "/article/show";
    }

    @GetMapping("/create")
    public String initCreateForm() {
        return "/article/create_form";
    }

    @PostMapping("/")
    public String ProceedCreateForm(ArticleCreateDto dto) {
        Long id = articleService.create(dto);
        return "redirect:/articles/" + id;
    }

    @GetMapping("/{id}/update")
    public String initUpdateForm(Model model,
                                 ArticleUpdateDto dto) {
        final ArticleTableRowDto article = articleService.retrieveTableRowById(dto.getId());
        model.addAttribute("article", article);
        return "/article/update_form";
    }

    @PutMapping("/{id}")
    public String processUpdateForm(ArticleUpdateDto dto) {
        articleService.update(dto);
        return "redirect:/articles/" + dto.getId();
    }

    @DeleteMapping("/{id}")
    public String delete(HttpSession session,
                         @PathVariable("id") Long id) {
        final Long userKey = (Long) session.getAttribute(SessionData.USER_KEY);
        articleService.delete(id, userKey);
        return "redirect:/";
    }
}
