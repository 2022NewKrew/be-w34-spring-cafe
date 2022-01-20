package com.kakao.cafe.util.aop;

import com.kakao.cafe.exception.user.NotAllowedUserException;
import com.kakao.cafe.exception.user.UserUnauthorizedException;
import com.kakao.cafe.model.dto.CommentDto;
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
public class MyCommentAspect {

    Logger logger = LoggerFactory.getLogger(MyArticleAspect.class);

    private final ArticleService articleService;
    private final HttpSession session;

    public MyCommentAspect(ArticleService articleService, HttpSession session) {
        this.articleService = articleService;
        this.session = session;
    }

    @Before("@annotation(com.kakao.cafe.util.annotation.MyComment) && args(articleId, commentId, ..)")
    public void myCommentCheck(JoinPoint joinPoint, int articleId, int commentId) {
        UserDto user = (UserDto) session.getAttribute("sessionedUser");
        if (user == null) {
            throw new UserUnauthorizedException();
        }

        CommentDto comment = articleService.filterCommentById(commentId);
        if (!user.getUserId().equals(comment.getWriter().getUserId())) {
            throw new NotAllowedUserException();
        }
    }
}
