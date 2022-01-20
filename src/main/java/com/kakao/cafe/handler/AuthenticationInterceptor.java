package com.kakao.cafe.handler;

import com.kakao.cafe.annotation.UserAuthorized;
import com.kakao.cafe.annotation.UserAuthorized.AuthCode;
import com.kakao.cafe.domain.auth.Auth;
import com.kakao.cafe.dto.article.ArticleDto;
import com.kakao.cafe.dto.article.ReplyDto;
import com.kakao.cafe.exception.SessionNotFoundException;
import com.kakao.cafe.exception.UnauthorizedAccessException;
import com.kakao.cafe.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.HandlerMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

import static com.kakao.cafe.annotation.UserAuthorized.AuthCode.*;

public class AuthenticationInterceptor implements HandlerInterceptor {

    private static Map<String, String> pathVariableMap = null;

    @Autowired
    private ArticleService articleService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        if (handler instanceof HandlerMethod) {
            handleUserAuthorization(request, (HandlerMethod) handler);
        }
        return true;
    }

    private void handleUserAuthorization(HttpServletRequest request, HandlerMethod handler) {
        Auth auth = (Auth) request.getSession().getAttribute("auth");
        UserAuthorized userAuthorized = handler.getMethodAnnotation(UserAuthorized.class);
        if (userAuthorized != null) {
            pathVariableMap = (Map<String, String>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
            AuthCode[] target = userAuthorized.target();
            validateByTarget(auth, target);
        }
    }

    private void validateByTarget(Auth auth, AuthCode[] target) {
        for (AuthCode authCode : target) {
            validate(auth, authCode);
        }
    }

    private void validate(Auth auth, AuthCode code) {
        if (code == SESSION) {
            validateSession(auth);
        }
        if (code == USER) {
            validateUser(auth);
        }
        if (code == ARTICLE) {
            validateArticle(auth);
        }
        if (code == REPLY) {
            validateReply(auth);
        }
    }

    private void validateSession(Auth auth) {
        if (auth == null) {
            throw new SessionNotFoundException("로그인 정보가 존재하지 않습니다.");
        }
    }

    private void validateArticle(Auth auth) {
        long articleId = getValidationKeyOrElseThrow("articleId");
        ArticleDto article = articleService.findArticleById(articleId);
        throwIfNotAuthorized(auth, article.getAuthorId());
    }

    private void validateUser(Auth auth) {
        long userId = getValidationKeyOrElseThrow("userId");
        throwIfNotAuthorized(auth, userId);
    }

    private void validateReply(Auth auth) {
        long replyId = getValidationKeyOrElseThrow("replyId");
        ReplyDto reply = articleService.findReplyById(replyId);
        throwIfNotAuthorized(auth, reply.getAuthorId());
    }

    private long getValidationKeyOrElseThrow(String key) {
        if (!pathVariableMap.containsKey(key)) {
            throw new AssertionError(String.format("인증에 필요한 %s 값이 없습니다.", key));
        }
        return Long.parseLong(pathVariableMap.get(key));
    }

    private void throwIfNotAuthorized(Auth auth, Long id) {
        if (auth.isNotValidId(id)) {
            throw new UnauthorizedAccessException("인가되지 않은 접근입니다.");
        }
    }
}
