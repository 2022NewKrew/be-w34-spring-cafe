package com.kakao.cafe.controller;

import com.kakao.cafe.annotation.RestResponseSessionCheck;
import com.kakao.cafe.dto.ReplyDTO;
import com.kakao.cafe.dto.RestResponseDTO;
import com.kakao.cafe.dto.SessionUserDTO;
import com.kakao.cafe.service.ArticleService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

@RestController
@RequestMapping("/api/articles")
public class ArticleRestController {
    @Resource(name = "articleService")
    private ArticleService articleService;

    @GetMapping("/{articleId}/reply/")
    @RestResponseSessionCheck
    RestResponseDTO getReply(@PathVariable long articleId) {
        return articleService.getArticleReplies(articleId, 0);
    }

    @PostMapping("/{articleId}/reply/")
    @RestResponseSessionCheck
    RestResponseDTO insertReply(@PathVariable long articleId, @Valid ReplyDTO reply, long lastReplyId, SessionUserDTO sessionUser) {

        return articleService.insertReplyAndGetReplies(reply, sessionUser.getId(), lastReplyId);
    }

    @DeleteMapping("/{articleId}/reply/{replyId}")
    @RestResponseSessionCheck
    RestResponseDTO deleteReply(@PathVariable long articleId, @PathVariable long replyId, SessionUserDTO sessionUser) {

        return articleService.deleteReply(sessionUser.getId(), articleId, replyId);

    }
}
