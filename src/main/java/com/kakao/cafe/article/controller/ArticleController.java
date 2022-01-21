package com.kakao.cafe.article.controller;

import lombok.RequiredArgsConstructor;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.kakao.cafe.article.dto.request.ArticleCreateRequestDTO;
import com.kakao.cafe.article.dto.request.ArticleUpdateRequestDTO;
import com.kakao.cafe.article.dto.request.ReplyCreateRequestDTO;
import com.kakao.cafe.article.dto.response.ArticleFindResponseDTO;
import com.kakao.cafe.article.dto.response.ReplyFindResponseDTO;
import com.kakao.cafe.article.service.ArticleService;
import com.kakao.cafe.article.service.ReplyService;
import com.kakao.cafe.common.Constant;
import com.kakao.cafe.common.SessionUser;
import com.kakao.cafe.common.annotation.LoginCheck;
import com.kakao.cafe.common.exception.UserAuthException;

@Controller
@RequestMapping("/articles")
@RequiredArgsConstructor
public class ArticleController {
    private final ArticleService articleService;
    private final ReplyService replyService;

    @LoginCheck
    @GetMapping("/{id}")
    public String getArticle(@PathVariable int id, Model model) {
        ArticleFindResponseDTO article = articleService.getArticleById(id);
        model.addAttribute("article", article);

        return "qna/show";
    }

    @LoginCheck
    @GetMapping("/new/form")
    public String getArticleForm() {
        return "qna/form";
    }

    @LoginCheck
    @GetMapping("/{id}/update/form")
    public String getUpdateForm(
            @SessionAttribute(Constant.SESSION_USER) SessionUser sessionUser,
            @PathVariable int id,
            Model model
    ) {
        ArticleFindResponseDTO article = articleService.getArticleById(id);

        if (!StringUtils.equals(sessionUser.getUserId(), article.getWriter())) {
            throw new UserAuthException("권한이 없습니다.");
        }

        model.addAttribute("article", article);
        return "qna/updateForm";
    }

    @LoginCheck
    @PostMapping("/create")
    public String createQna(ArticleCreateRequestDTO requestDTO) {
        articleService.create(requestDTO);

        return "redirect:/";
    }

    @LoginCheck
    @PostMapping("/{id}/update")
    public String updateArticle(
            @SessionAttribute(Constant.SESSION_USER) SessionUser sessionUser,
            @PathVariable int id,
            ArticleUpdateRequestDTO requestDTO
    ) {
        if (!StringUtils.equals(sessionUser.getUserId(), requestDTO.getWriter())) {
            throw new UserAuthException("권한이 없습니다.");
        }

        articleService.update(id, requestDTO);

        return "redirect:/";
    }

    @LoginCheck
    @PostMapping("/{id}/remove")
    public String removeArticle(
            @SessionAttribute(Constant.SESSION_USER) SessionUser sessionUser,
            @PathVariable int id
    ) {
        ArticleFindResponseDTO article = articleService.getArticleById(id);

        if (!StringUtils.equals(sessionUser.getUserId(), article.getWriter())) {
            throw new UserAuthException("권한이 없습니다.");
        }

        articleService.remove(id);

        return "redirect:/";
    }

    @LoginCheck
    @PostMapping("/{id}/answers")
    public String createAnswer(
            @PathVariable int id,
            @SessionAttribute(Constant.SESSION_USER) SessionUser sessionUser,
            ReplyCreateRequestDTO requestDTO
    ) {
        requestDTO.init(id, sessionUser.getUserId());
        replyService.create(requestDTO);

        return "redirect:/articles/" + id;
    }

    @LoginCheck
    @PostMapping("/{id}/answers/{replyId}/remove")
    public String removeAnswer(
            @PathVariable int id,
            @PathVariable int replyId,
            @SessionAttribute(Constant.SESSION_USER) SessionUser sessionUser
    ) {
        ReplyFindResponseDTO reply = replyService.getReplyById(replyId);
        if (!StringUtils.equals(sessionUser.getUserId(), reply.getWriter())) {
            throw new UserAuthException("권한이 없습니다.");
        }

        replyService.remove(replyId);

        return "redirect:/articles/" + id;
    }
}
