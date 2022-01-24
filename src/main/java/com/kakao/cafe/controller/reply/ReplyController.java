package com.kakao.cafe.controller.reply;

import static com.kakao.cafe.controller.Constant.PERMISSION_EXCEPTION_MESSAGE_ONLY_LOGIN_USER;

import com.kakao.cafe.exception.IllegalPermissionException;
import com.kakao.cafe.service.reply.ReplyService;
import com.kakao.cafe.service.reply.dto.ReplyCreateDto;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ReplyController {

    private final ReplyService replyService;

    public ReplyController(ReplyService replyService) {
        this.replyService = replyService;
    }

    @PostMapping("/replies")
    public String postReply(ReplyCreateDto replyCreateDto, HttpSession session) {
        if (session.isNew()) {
            throw new IllegalPermissionException(PERMISSION_EXCEPTION_MESSAGE_ONLY_LOGIN_USER);
        }
        replyService.createReply(replyCreateDto);
        return "redirect:/articles?id=" + replyCreateDto.getArticleId();
    }

    @DeleteMapping("/replies")
    public String deleteReply(int id, int replyId, HttpSession session) {
        if (session.isNew()) {
            throw new IllegalPermissionException(PERMISSION_EXCEPTION_MESSAGE_ONLY_LOGIN_USER);
        }

        String loginUserId = (String) session.getAttribute("loginUserId");
        replyService.checkPermission(replyId, loginUserId);

        replyService.deleteReply(replyId);
        return "redirect:/articles?id=" + id;
    }
}
