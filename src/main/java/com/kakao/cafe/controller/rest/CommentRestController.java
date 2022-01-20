package com.kakao.cafe.controller.rest;

import com.kakao.cafe.aop.login.LoginIdSessionNotNull;
import com.kakao.cafe.dto.comment.SaveCommentDto;
import com.kakao.cafe.dto.comment.CommentViewDto;
import com.kakao.cafe.service.CommentService;
import com.kakao.cafe.util.SessionUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class CommentRestController {

    private final CommentService commentService;

    private final HttpSession session;

    @GetMapping("/comments")
    public List<CommentViewDto> getCommentViewDtoListForPost(@RequestParam("postId") Long postId) {
        return commentService.getCommentViewDtoListByPostId(postId);
    }

    @PostMapping("/comments")
    @LoginIdSessionNotNull
    public void addComment(SaveCommentDto saveCommentDto) {
        Long loginUserId = SessionUtil.getLoginUserId(session);
        commentService.addComment(saveCommentDto, loginUserId);
    }

    @PutMapping("/comments")
    @LoginIdSessionNotNull
    public void updateComment(SaveCommentDto saveCommentDto) {
        Long loginUserId = SessionUtil.getLoginUserId(session);
        commentService.updateComment(saveCommentDto, loginUserId);
    }

    @DeleteMapping("/comments")
    @LoginIdSessionNotNull
    public void deleteComment(@RequestParam("commentId") Long commentId) {
        Long loginUserId = SessionUtil.getLoginUserId(session);
        commentService.deleteByCommentIdAndUserId(commentId, loginUserId);
    }
}
