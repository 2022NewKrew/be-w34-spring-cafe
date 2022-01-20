package com.kakao.cafe.reply.controller;

import com.kakao.cafe.config.Authorized;
import com.kakao.cafe.home.dto.SessionUser;
import com.kakao.cafe.reply.service.ReplyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

@Controller
@RequiredArgsConstructor
@RequestMapping("/replies")
@Authorized
public class ReplyController {
    private final ReplyService replyService;

    @PostMapping
    public String saveReply(Long articleId, String comment, @SessionAttribute SessionUser sessionUser) {
        replyService.saveReply(articleId, sessionUser.getName(), comment);
        return "redirect:/articles/" + articleId;
    }
}
