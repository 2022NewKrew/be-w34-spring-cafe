package com.kakao.cafe.article.controller;

import com.kakao.cafe.article.dto.request.ArticleReqDto;
import com.kakao.cafe.article.dto.response.ArticleDetailResDto;
import com.kakao.cafe.article.service.ArticleService;
import com.kakao.cafe.config.Authorized;
import com.kakao.cafe.exception.SessionUserNotFoundException;
import com.kakao.cafe.home.dto.SessionUser;
import com.kakao.cafe.reply.dto.ReplyResDto;
import com.kakao.cafe.reply.service.ReplyService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/articles")
@Authorized
public class ArticleController {
    private final ArticleService articleService;
    private final ReplyService replyService;

    @PostMapping
    public String saveArticle(@ModelAttribute ArticleReqDto articleReqDto) {
        articleService.saveArticle(articleReqDto);
        return "redirect:/";
    }

    @GetMapping("/{id}")
    public String showArticle(@PathVariable Long id, Model model) {
        ArticleDetailResDto article = articleService.getArticle(id);
        List<ReplyResDto> replyList = replyService.getReplyListByArticleId(id);

        model.addAttribute("article", article);
        model.addAttribute("replies", replyList);

        return "/qna/show";
    }

    @GetMapping("/form")
    public String showRegisterForm() {
        return "/qna/form";
    }

    @GetMapping("/{id}/form")
    public String showUpdateForm(@PathVariable Long id, Model model, @SessionAttribute SessionUser sessionUser) {
        ArticleDetailResDto article = articleService.getArticleForUpdate(id, sessionUser.getName());
        model.addAttribute("article", article);
        return "/qna/updateForm";
    }

    @PutMapping
    public String updateArticle(@ModelAttribute ArticleReqDto articleReqDto) {
        articleService.update(articleReqDto);
        return "redirect:/articles/" + articleReqDto.getId();
    }

    @DeleteMapping
    public String deleteArticle(@ModelAttribute ArticleReqDto articleReqDto, @SessionAttribute SessionUser sessionUser) {
        articleService.delete(articleReqDto.getId(), sessionUser.getName());
        return "redirect:/";
    }

    @DeleteMapping("/{articleId}/replies/{replyId}")
    public String deleteReply(@PathVariable Long articleId, @PathVariable Long replyId, @SessionAttribute SessionUser sessionUser) {
        replyService.delete(articleId, replyId, sessionUser.getName());
        return "redirect:/articles/" + articleId;
    }
}
