package com.kakao.cafe.web;

import com.kakao.cafe.domain.entity.User;
import com.kakao.cafe.dto.reply.ReplyCreateCommand;
import com.kakao.cafe.service.ReplyService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

@Controller
public class ReplyController {
    private final ReplyService replyService;

    public ReplyController(ReplyService replyService) {
        this.replyService = replyService;
    }

    @PostMapping("/questions/{articleId}/answers/")
    public String writeReply(@PathVariable long articleId,
                             @SessionAttribute("sessionedUser") User user,
                             String contents) {
        replyService.createReply(new ReplyCreateCommand(articleId, user.getUserId(), contents));
        return "redirect:/articles/{articleId}";
    }

    @DeleteMapping("/questions/{articleId}/answers/{replyId}")
    public String deleteReply(@PathVariable long replyId) {
        replyService.deleteReply(replyId);
        return "redirect:/articles/{articleId}";
    }
}
