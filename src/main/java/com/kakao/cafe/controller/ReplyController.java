package com.kakao.cafe.controller;

import com.kakao.cafe.controller.dto.ReplyDto;
import com.kakao.cafe.service.ReplyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("articles/{articleId}/replies")
@RequiredArgsConstructor
public class ReplyController {

    private final ReplyService replyService;

    @PostMapping
    public String createReply(@PathVariable int articleId, @ModelAttribute ReplyDto replyDto, HttpSession httpSession) {
        replyService.create(replyDto);
        return String.format("redirect:/articles/%d", articleId);
    }

    @DeleteMapping("{id}")
    public String deleteReply(@PathVariable int articleId, @PathVariable int id, HttpSession httpSession) {
        Object sessionId = httpSession.getAttribute("sessionId");
        replyService.delete(id, String.valueOf(sessionId));
        return String.format("redirect:/articles/%d", articleId);
    }




}
