package com.kakao.cafe.util.aop;

import com.kakao.cafe.exception.user.NotAllowedUserException;
import com.kakao.cafe.exception.user.UserUnauthorizedException;
import com.kakao.cafe.model.dto.ArticleDto;
import com.kakao.cafe.model.dto.UserDto;
import com.kakao.cafe.service.ArticleService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;

@Aspect
@Component
public class MyArticleAspect {

    Logger logger = LoggerFactory.getLogger(MyArticleAspect.class);

    private final ArticleService articleService;
    private final HttpSession session;

    public MyArticleAspect(ArticleService articleService, HttpSession session) {
        this.articleService = articleService;
        this.session = session;
    }

    @Before("@annotation(com.kakao.cafe.util.annotation.MyArticle) && args(articleId, ..)")
    public void myArticleCheck(JoinPoint joinPoint, int articleId) throws Exception {
        UserDto user = (UserDto) session.getAttribute("sessionedUser");
        if (user == null) {
            throw new UserUnauthorizedException();
        }

        ArticleDto article = articleService.filterArticleById(articleId);
        if (!user.getUserId().equals(article.getWriter().getUserId())) {
            throw new NotAllowedUserException();
        }
    }
}

