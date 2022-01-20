package com.kakao.cafe.controller;

import com.kakao.cafe.model.comment.CommentWriteRequest;
import com.kakao.cafe.service.comment.CommentService;
import com.kakao.cafe.utils.SessionUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Slf4j
@RequiredArgsConstructor
@Controller
public class PostCommentController {

    private final CommentService commentService;

    @PostMapping("/posts/{postId}/comments")
    public String registerComment(@PathVariable long postId, @Valid CommentWriteRequest request, HttpSession session, RedirectAttributes rttr) {
        long currentUserId = SessionUtils.getCurrentUserId(session);
        commentService.saveComment(postId, currentUserId, request);
        rttr.addFlashAttribute("msg", "댓글을 등록하였습니다.");
        return "redirect:/posts/" + postId;
    }

    @DeleteMapping("/posts/{postId}/comments/{id}")
    public String deleteComment(@PathVariable long postId, @PathVariable long id, HttpSession session, RedirectAttributes rttr) {
        long currentUserId = SessionUtils.getCurrentUserId(session);
        commentService.deleteById(id, postId, currentUserId);
        rttr.addFlashAttribute("msg", "댓글을 삭제하였습니다.");
        return "redirect:/posts/" + postId;
    }

}
