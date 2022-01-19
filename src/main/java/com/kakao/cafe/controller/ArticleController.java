package com.kakao.cafe.controller;

import com.kakao.cafe.controller.error.ErrorMessageBox;
import com.kakao.cafe.core.exception.IsNotAuthorOfThisArticle;
import com.kakao.cafe.domain.article.dto.ArticleResponse;
import com.kakao.cafe.domain.article.dto.ArticleSaveForm;
import com.kakao.cafe.domain.article.dto.ArticleUpdateForm;
import com.kakao.cafe.domain.reply.ReplyService;
import com.kakao.cafe.domain.reply.dto.ReplyResponse;
import com.kakao.cafe.domain.user.User;
import com.kakao.cafe.core.SessionConst;
import com.kakao.cafe.domain.article.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@Controller
@RequiredArgsConstructor
@RequestMapping("/articles")
public class ArticleController {

    private final ArticleService articleService;
    private final ReplyService replyService;

    @PostMapping("")
    public String createQuestion(@Valid @ModelAttribute ArticleSaveForm articleSaveForm, @SessionAttribute(name = SessionConst.LOGIN_COOKIE) User user) {
        articleService.save(articleSaveForm, user);
        return "redirect:/";
    }

    @GetMapping("/{id}")
    public String getArticleInfo(@PathVariable Long id, Model model) {
        ArticleResponse article = articleService.findById(id);
        model.addAttribute("article", article);

        List<ReplyResponse> replyList = replyService.getComments(id);
        model.addAttribute("replyList", replyList);
        return "qna/detail";
    }

    @GetMapping("/{id}/form")
    public String updateForm(@PathVariable Long id, Model model, @SessionAttribute(name = SessionConst.LOGIN_COOKIE) User user) {
        try {
            ArticleUpdateForm updateForm = articleService.getUpdateForm(id, user.getId());
            model.addAttribute("article", updateForm);
            return "qna/updateForm";
        } catch (IsNotAuthorOfThisArticle e) {
            model.addAttribute("error", new ErrorMessageBox("권한이 없습니다."));
            return "/error";
        }
    }

    @PutMapping("/{id}")
    public String update(@PathVariable Long id, Model model, @ModelAttribute ArticleUpdateForm updateForm, @SessionAttribute(name = SessionConst.LOGIN_COOKIE) User user) {
        try {
            articleService.update(id, updateForm, user.getId());
            return "redirect:/articles/" + id;
        } catch (IsNotAuthorOfThisArticle e) {
            model.addAttribute("error", new ErrorMessageBox("권한이 없습니다."));
            return "/error";
        }
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id, Model model, @SessionAttribute(name = SessionConst.LOGIN_COOKIE) User user) {
        try {
            articleService.delete(id, user.getId());
            return "redirect:/";
        } catch (Exception e) {
            model.addAttribute("error", new ErrorMessageBox("삭제할 수 없습니다."));
            return "/error";
        }
    }
}
