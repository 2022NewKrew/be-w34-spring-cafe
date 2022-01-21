package com.kakao.cafe.controller;

import com.kakao.cafe.domain.Reply;
import com.kakao.cafe.domain.User;
import com.kakao.cafe.interceptor.AuthenticationSecured;
import com.kakao.cafe.interceptor.PersonalAuthorizationSecured;
import com.kakao.cafe.service.ReplyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@Slf4j
@RequiredArgsConstructor
public class ReplyController {

    private final ReplyService replyService;

    @PostMapping("/articles/{articleId}/reply")
    @AuthenticationSecured
    public String create(@PathVariable int articleId, HttpServletRequest request) {
        User sessionedUser = (User) request.getSession().getAttribute("sessionedUser");
        String contents = request.getParameter("contents");
        int userPk = sessionedUser.getId();

        replyService.create(new Reply(0, articleId, sessionedUser.getName(), contents, userPk, false));
        return "redirect:/articles/" + articleId + "?userPk=" + userPk;
    }

    @DeleteMapping("articles/{articleId}/reply/{id}")
    @AuthenticationSecured
    @PersonalAuthorizationSecured
    public String delete(@PathVariable int articleId, @PathVariable int id, HttpServletRequest request) {
        User sessionedUser = (User) request.getSession().getAttribute("sessionedUser");
        int userPk = sessionedUser.getId();

        replyService.delete(id);
        return "redirect:/articles/" + articleId + "?userPk=" + userPk;
    }
}
