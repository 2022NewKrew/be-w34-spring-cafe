package com.kakao.cafe.controller;

import com.kakao.cafe.domain.reply.Reply;
import com.kakao.cafe.domain.user.User;
import com.kakao.cafe.service.ReplyService;
import com.kakao.cafe.util.Constant;
import com.kakao.cafe.util.ErrorMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;

@Slf4j
@RequiredArgsConstructor
@Controller
public class ReplyController {
    private final ReplyService replyService;

    @PostMapping("/qna/{articleId}/reply")
    public String writeReply(@PathVariable Long articleId, Reply reply, HttpSession session) {
        Long writerId = loginCheck(session);
        reply.setWriterId(writerId);
        reply.setArticleId(articleId);
        reply.setDeleted(false);
        replyService.join(reply);
        return "redirect:/articles/" + articleId;
    }

    @DeleteMapping("/qna/{articleId}/replies/{id}")
    public String deleteReply(@PathVariable Long articleId, @PathVariable Long id, Model model, HttpSession session) {
        Long writerId = loginCheck(session);
        Reply reply = replyService.findOne(id);
        if (!writerId.equals(reply.getWriterId())) {
            throw new IllegalStateException(ErrorMessage.DELETE_FORBIDDEN.getMsg());
        }
        replyService.deleteReply(id);
        return "redirect:/articles/" + articleId;
    }

    private Long loginCheck(HttpSession session) {
        User user = (User) session.getAttribute(Constant.LOGIN_SESSION);
        if (user == null) {
            throw new IllegalStateException(ErrorMessage.UPDATE_NON_LOGIN.getMsg());
        }
        return user.getUserId();
    }
}
