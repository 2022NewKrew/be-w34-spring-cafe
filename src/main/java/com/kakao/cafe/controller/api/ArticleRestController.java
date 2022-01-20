package com.kakao.cafe.controller.api;

import com.kakao.cafe.controller.dto.SessionUser;
import com.kakao.cafe.controller.interceptor.Authenticated;
import com.kakao.cafe.domain.article.ArticleService;
import com.kakao.cafe.domain.article.dto.ReplyRequestDto;
import com.kakao.cafe.domain.article.dto.ReplyResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@Authenticated
@RestController
public class ArticleRestController {

    private final ArticleService articleService;

    public ArticleRestController(ArticleService articleService) {
        this.articleService = articleService;
    }
    
    @GetMapping("/api/v1/articles/{articleId}/reply/{replyId}")
    public ResponseEntity<?> getReply(@PathVariable Long articleId, @PathVariable Long replyId) {
        ReplyResponseDto reply = articleService.retrieveReply(articleId, replyId);
        return ResponseEntity.ok(reply);
    }

    @PostMapping("/api/v1/articles/{articleId}/reply")
    public ResponseEntity<?> createReply(@PathVariable Long articleId, @RequestBody ReplyRequestDto replyRequestDto, SessionUser sessionUser) {
        Long createdId = articleService.createReply(articleId, replyRequestDto, sessionUser.getUserId());
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri()
                .path("/{replyId}")
                .build(createdId);
        return ResponseEntity.created(uri).body(createdId);
    }

    @DeleteMapping("/api/v1/articles/{articleId}/reply/{replyId}")
    public ResponseEntity<?> deleteReply(@PathVariable Long articleId, @PathVariable Long replyId, SessionUser sessionUser) {
        Long deletedId = articleService.deleteReply(articleId, replyId, sessionUser.getUserId());
        return ResponseEntity.ok(deletedId);
    }
}
