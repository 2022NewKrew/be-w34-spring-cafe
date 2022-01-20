package com.kakao.cafe.article.adapter.in.web;

import com.kakao.cafe.article.application.port.in.CommentRegistrationUseCase;
import com.kakao.cafe.common.meta.SessionData;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.security.auth.login.LoginException;
import javax.servlet.http.HttpSession;

@Controller
public class CommentRegistrationController {
    private final CommentRegistrationUseCase commentRegistrationUseCase;

    public CommentRegistrationController(CommentRegistrationUseCase commentRegistrationUseCase) {
        this.commentRegistrationUseCase = commentRegistrationUseCase;
    }

    @PostMapping("/comments/{articleId}")
    public String registerComment(@RequestParam("comment") String comment,
                                  @PathVariable("articleId") Long articleId,
                                  HttpSession session) throws LoginException {
        Object userNicknameObj = session.getAttribute(SessionData.USER_NAME);
        Object userKeyObj = session.getAttribute(SessionData.USER_KEY);
        if (userNicknameObj == null || userKeyObj == null) throw new LoginException();
        String userNickname = (String) userNicknameObj;
        Long userKey = (Long) userKeyObj;
        commentRegistrationUseCase.registerComment(comment, userNickname, userKey, articleId);
        return "redirect:/articles/" + articleId;
    }
}
