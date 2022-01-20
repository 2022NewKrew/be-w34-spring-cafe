package com.kakao.cafe.util.aop;

import com.kakao.cafe.exception.user.NotAllowedUserException;
import com.kakao.cafe.exception.user.UserUnauthorizedException;
import com.kakao.cafe.model.dto.CommentDto;
import com.kakao.cafe.model.dto.UserDto;
import com.kakao.cafe.service.ArticleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;

@Aspect
@Component
@RequiredArgsConstructor
@Slf4j
public class MyCommentAspect {

    private final ArticleService articleService;
    private final HttpSession session;

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
