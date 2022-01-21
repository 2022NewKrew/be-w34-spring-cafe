package com.kakao.cafe.web;

import com.kakao.cafe.domain.entity.User;
import com.kakao.cafe.dto.reply.ReplyContents;
import com.kakao.cafe.dto.reply.ReplyCreateCommand;
import com.kakao.cafe.service.ReplyService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.naming.NoPermissionException;

@Controller
public class ReplyController {
    private final ReplyService replyService;

    public ReplyController(ReplyService replyService) {
        this.replyService = replyService;
    }

    @RequestMapping(value = "/questions/{articleId}/answers/", method = RequestMethod.POST)
    public String writeReply(@PathVariable long articleId,
                             @RequestAttribute("sessionedUser") User user,
                             String contents) {
        replyService.createReply(new ReplyCreateCommand(articleId, user.getUserId(), contents));
        return "redirect:/articles/{articleId}";
    }

    @RequestMapping(value = "/questions/{articleId}/answers/{replyId}",
            method = RequestMethod.DELETE)
    public String deleteReply(@PathVariable long replyId,
                              @RequestAttribute("sessionedUser") User user) {
        try {
            checkPermission(user, replyId);
            replyService.deleteReply(replyId);
            return "redirect:/articles/{articleId}";
        } catch (NoPermissionException e) {
            return "redirect:/nopermission";
        }
    }

    private ReplyContents checkPermission(User user, long replyId) throws NoPermissionException{
        ReplyContents replyContents = replyService.getReply(replyId);
        if (userNotPermittedToReply(user, replyContents)) {
            throw new NoPermissionException();
        }
        return replyContents;
    }

    private boolean userNotPermittedToReply(User user, ReplyContents replyContents) {
        return !user.getUserId().equals(replyContents.getWriterId());
    }
}
