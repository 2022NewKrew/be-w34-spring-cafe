package com.kakao.cafe.controller;

import com.kakao.cafe.dto.ReplyRequestDto;
import com.kakao.cafe.entity.User;
import com.kakao.cafe.service.ReplyService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;

@Controller
public class ReplyController {
    private final ReplyService replyService;

    public ReplyController(ReplyService replyService) {
        this.replyService = replyService;
    }

    @PostMapping("/articles/{index}")
    public String createReply(@PathVariable String index, ReplyRequestDto replyRequestDto, HttpSession httpSession) {
        User sessionedUser = (User) httpSession.getAttribute("sessionedUser");
        if(sessionedUser == null)
            return "redirect:/user/login";

        replyService.createRelpy(index, replyRequestDto, sessionedUser);
        return "redirect:/articles/{index}";
    }

    @DeleteMapping("/articles/{articleId}/reply/{replyId}")
    public String deleteReply() {
        return "redirect:/articles/{articleId}";
    }

}
