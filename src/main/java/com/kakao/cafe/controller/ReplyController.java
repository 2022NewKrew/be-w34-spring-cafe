package com.kakao.cafe.controller;

import com.kakao.cafe.domain.Reply;
import com.kakao.cafe.service.ReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/replies")
public class ReplyController {
    private final ReplyService replyService;

    @Autowired
    public ReplyController(ReplyService replyService) {
        this.replyService = replyService;
    }

    @PostMapping
    public String addReply(@ModelAttribute Reply reply, RedirectAttributes redirectAttributes) {
        replyService.addReply(reply);
        return "redirect:/articles/" + reply.getFK_article_id();
    }

    @DeleteMapping("{id}")
    public String deleteById(@PathVariable String id, @RequestParam String writer, @RequestParam String articleId, HttpSession httpSession) {
        replyService.isWriter(writer, httpSession);
        replyService.deleteById(id);
        return "redirect:/articles/" + articleId;
    }
}
