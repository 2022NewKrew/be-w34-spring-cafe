package com.kakao.cafe.controller;

import com.kakao.cafe.dto.reply.ReplyRequest;
import com.kakao.cafe.dto.user.SessionUser;
import com.kakao.cafe.service.reply.ReplyService;
import com.kakao.cafe.util.auth.LoginCheck;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Controller
public class ReplyController {

    private final ReplyService replyService;

    @PostMapping("/articles/{articleId}/replies")
    public String createReply(@PathVariable Long articleId, @LoginCheck SessionUser sessionUser, String contents){
        ReplyRequest replyRequest = ReplyRequest.builder()
                .articleId(articleId)
                .writer(sessionUser.getUserId())
                .contents(contents)
                .createdAt(LocalDateTime.now())
                .build();
        replyService.addReply(replyRequest);
        return "redirect:/articles/{articleId}";
    }
}
