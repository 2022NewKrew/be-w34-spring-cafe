package com.kakao.cafe.app.controller;

import com.kakao.cafe.app.request.ReplyRequest;
import com.kakao.cafe.service.ReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
public class ReplyController {

    private final ReplyService service;

    @Autowired
    public ReplyController(ReplyService service) {
        this.service = service;
    }

    @PostMapping("/articles/{articleId}/replies")
    public String write(
            @Valid @ModelAttribute ReplyRequest request,
            @PathVariable("articleId") long targetId,
            HttpSession session
    ) {
        Long authorId = (Long) session.getAttribute("currentUserId");
        if (authorId == null) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "not logged in");
        }
        service.create(targetId, authorId, request.toDraftDto());
        return "redirect:/articles/" + targetId;
    }


    @DeleteMapping("/articles/{articleId}/replies/{replyId}")
    public String delete(
            @PathVariable("articleId") long articleId,
            @PathVariable("replyId") long replyId,
            HttpSession session
    ) {
        Long actorId = (Long) session.getAttribute("currentUserId");
        if (actorId == null) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "not logged in");
        }
        service.delete(replyId, actorId);
        return "redirect:/articles/" + articleId;
    }
}
