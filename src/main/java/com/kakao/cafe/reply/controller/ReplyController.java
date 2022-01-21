package com.kakao.cafe.reply.controller;

import com.kakao.cafe.annotaion.LoginCheck;
import com.kakao.cafe.reply.factory.ReplyFactory;
import com.kakao.cafe.reply.service.ReplyService;
import com.kakao.cafe.user.domain.User;
import com.kakao.cafe.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;

import static com.kakao.cafe.Util.SessionUtil.getUserIdFromSession;

@Controller
@RequiredArgsConstructor
public class ReplyController {

    private final UserService userService;
    private final ReplyService replyService;

    @LoginCheck
    @PostMapping("/reply/{id}")
    public String createReply(@PathVariable("id") Long articleId, String replyContents, HttpSession session) {
        String userId = getUserIdFromSession(session);
        User user = userService.findByUserId(userId);
        replyService.createReply(ReplyFactory.toComment(articleId, user.getId(), user.getName(), replyContents));

        return "redirect:/articles/" + articleId;
    }
}
