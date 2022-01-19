package com.kakao.cafe.reply.controller;

import com.kakao.cafe.reply.service.ReplyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequiredArgsConstructor
@RequestMapping("/replies")
public class ReplyController {
    private final ReplyService replyService;

    @PostMapping
    public String saveReply(Long articleId, String comment, HttpSession session) {
        String sessionUserId = (String) session.getAttribute("sessionUserId"); // todo change
        replyService.saveReply(articleId, sessionUserId, comment);
        return "redirect:/articles/" + articleId;
    }
}
