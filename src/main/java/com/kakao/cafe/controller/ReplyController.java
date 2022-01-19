package com.kakao.cafe.controller;

import com.kakao.cafe.dto.reply.ReplyCreateDto;
import com.kakao.cafe.service.ReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;

@Controller
public class ReplyController {
    private final ReplyService replyService;

    @Autowired
    public ReplyController(ReplyService replyService) {
        this.replyService = replyService;
    }

    @PostMapping("/questions/{questionId}/answers")
    public String createAnswer(@PathVariable int questionId, ReplyCreateDto replyCreateDto, HttpSession session) {
        replyService.create(replyCreateDto, questionId, session);
        return "redirect:/questions/" + questionId;
    }

    @DeleteMapping("/questions/{questionId}/answers/{answerId}")
    public String deleteAnswer(@PathVariable int questionId,
                               @PathVariable int answerId,
                               HttpSession session) {
        replyService.delete(answerId, session);
        return "redirect:/questions/" + questionId;
    }
}
