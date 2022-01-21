package com.kakao.cafe.web.controller;

import com.kakao.cafe.domain.Reply;
import com.kakao.cafe.domain.Users;
import com.kakao.cafe.web.service.ReplyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Controller
public class ReplyApiController {

    private final ReplyService replyService;

    Logger logger = LoggerFactory.getLogger(ReplyApiController.class);

    private ReplyApiController(ReplyService replyService) {
        this.replyService = replyService;
    }

    @PostMapping("articles/{id}/replies")
    String createReply(@PathVariable int id, Reply reply, HttpSession session) {
        Users replier = (Users) session.getAttribute("sessionedUser");
        if (replier == null)
            return "redirect:/users/login";
        replyService.addReply(id, replier, reply);
        logger.info("Reply API: 답글이 등록됐습니다.");
        return "redirect:/articles/{id}";
    }

    @DeleteMapping("articles/{articleId}/replies/{replyId}")
    String deleteReply(@PathVariable int articleId, @PathVariable int replyId, HttpSession session) {
        Users currentUser = (Users) session.getAttribute("sessionedUser");

        if (replyService.getReplyById(replyId).getAuthor().getId() != currentUser.getId())
            return "redirect:/articles/{articleId}";
        replyService.deleteReply(replyId);
        logger.info("Reply API: 답글이 삭제됐습니다.");
        return "redirect:/articles/{articleId}";
    }

    @GetMapping("articles/{articleId}/replies/{replyId}/update")
    String getReplyUpdateForm(@PathVariable int articleId, @PathVariable int replyId, HttpSession session, Model model) {
        Users currentUser = (Users) session.getAttribute("sessionedUser");
        if (replyService.getReplyById(replyId).getAuthor().getId() != currentUser.getId())
            return "redirect:/articles/{articleId}";
        model.addAttribute("reply", replyService.getReplyById(replyId));
        return "articles/updateReplyForm";
    }

    @PutMapping("articles/{articleId}/replies/{replyId}")
    String updateReply(@PathVariable int articleId, @PathVariable int replyId, Reply updatedReply, HttpSession session) {
        Users currentUser = (Users) session.getAttribute("sessionedUser");

        if (replyService.getReplyById(replyId).getAuthor().getId() != currentUser.getId())
            return "redirect:/articles/{articleId}";
        replyService.updateReply(replyId, updatedReply);
        logger.info("Reply API: 답글이 수정됐습니다.");
        return "redirect:/articles/{articleId}";
    }
}
