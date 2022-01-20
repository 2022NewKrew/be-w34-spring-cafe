package com.kakao.cafe.web;

import com.kakao.cafe.domain.entity.User;
import com.kakao.cafe.dto.article.ArticleContents;
import com.kakao.cafe.dto.reply.ReplyContents;
import com.kakao.cafe.service.ArticleService;
import com.kakao.cafe.service.ReplyService;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.HandlerMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Map;

public class LoginInterceptor implements HandlerInterceptor {

    @Resource
    private ArticleService articleService;
    @Resource
    private ReplyService replyService;

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {

        HttpSession session = request.getSession();
        User user = (User)session.getAttribute("sessionedUser");
        if (user == null) {
            response.sendRedirect("/users/login");
            return false;
        }

        Map pathVariables = (Map) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
        if (pathVariables.size() == 0) {
            return true;
        }

        if (pathVariables.containsKey("userId")) {
            String userId = (String) pathVariables.get("userId");
            if (userId != null && userNotPermittedToUser(user, userId)) {
                response.sendRedirect("/nopermission");
                return false;
            }
        } else if (pathVariables.containsKey("replyId")) {
            long replyId = Long.parseLong((String) pathVariables.get("replyId"));
            ReplyContents replyContents = replyService.getReply(replyId);
            if (userNotPermittedToReply(user, replyContents)) {
                response.sendRedirect("/nopermission");
                return false;
            }
        } else if (pathVariables.containsKey("articleId")) {
            if (request.getServletPath().contains("answers")) {
                return true;
            }
            long articleId = Long.parseLong((String) pathVariables.get("articleId"));
            ArticleContents articleContents = articleService.getArticle(articleId);
            if (userNotPermittedToArticle(user, articleContents) && !isRequestGetArticle(request)) {
                response.sendRedirect("/nopermission");
                return false;
            }
        }

        return true;
    }

    private boolean userNotPermittedToUser(User user, String userId) {
        return !user.getUserId().equals(userId);
    }

    private boolean userNotPermittedToArticle(User user, ArticleContents articleContents) {
        return !user.getUserId().equals(articleContents.getWriterId());
    }

    private boolean isRequestGetArticle(HttpServletRequest request) {
        AntPathMatcher pathMatcher = new AntPathMatcher();
        return pathMatcher.match("/articles/{articleId}", request.getServletPath());
    }
    private boolean userNotPermittedToReply(User user, ReplyContents replyContents) {
        return !user.getUserId().equals(replyContents.getWriterId());
    }
}
