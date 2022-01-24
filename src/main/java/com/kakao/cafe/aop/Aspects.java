package com.kakao.cafe.aop;

import com.kakao.cafe.application.service.BoardService;
import com.kakao.cafe.util.annotation.BoardCheck;
import com.kakao.cafe.util.annotation.LoginCheck;
import com.kakao.cafe.util.exception.NoAdminException;
import com.kakao.cafe.util.exception.NotLoggedInException;
import com.kakao.cafe.util.exception.NotMyArticleException;
import com.kakao.cafe.util.exception.NotMyCommentException;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpSession;

@Component
@Aspect
public class Aspects {
    private final Logger logger = LoggerFactory.getLogger(Aspects.class);
    private BoardService boardService;

    @Lazy
    @Autowired
    private void setBoardService(BoardService boardService) {
        this.boardService = boardService;
    }

    @Before(value = "execution(* com.kakao.cafe..*.*(..))")
    public void logging(JoinPoint joinPoint) {
        logger.debug("Method Call: {}", joinPoint.getSignature());
    }

    @Before("@annotation(com.kakao.cafe.util.annotation.LoginCheck) && @annotation(loginCheck)")
    public void loginCheckUsingAnnotation(LoginCheck loginCheck) {
        HttpSession session = ((ServletRequestAttributes) (RequestContextHolder.currentRequestAttributes())).getRequest().getSession();
        String loggedInId = (String) session.getAttribute("USER_ID");

        if (loggedInId == null) {
            throw new NotLoggedInException();
        }

        if (loginCheck.type().toString().equals("ADMIN") && !loggedInId.equals("admin")) {
            throw new NoAdminException();
        }
    }

    @Before("execution(* com.kakao.cafe.controller.BoardController.*(..)) && !execution(* com.kakao.cafe.controller.BoardController.goAllView(..))")
    public void loginCheckForBoard() {
        HttpSession session = ((ServletRequestAttributes) (RequestContextHolder.currentRequestAttributes())).getRequest().getSession();
        String loggedInId = (String) session.getAttribute("USER_ID");

        if (loggedInId == null) {
            throw new NotLoggedInException();
        }
    }

    @Before("execution(* com.kakao.cafe.controller.BoardController.*(..)) && @annotation(com.kakao.cafe.util.annotation.BoardCheck) && @annotation(boardCheck)")
    public void boardMineCheckUsingAnnotation(JoinPoint joinPoint, BoardCheck boardCheck) {
        HttpSession session = ((ServletRequestAttributes) (RequestContextHolder.currentRequestAttributes())).getRequest().getSession();
        String loggedInId = (String) session.getAttribute("USER_ID");

        if (boardCheck.type().toString().equals("ARTICLE") && !(boardService.isSameArticleWriter((long) joinPoint.getArgs()[0], loggedInId))) {
            throw new NotMyArticleException();
        }

        if (boardCheck.type().toString().equals("COMMENT")
                && !(boardService.isSameCommentWriter((long) joinPoint.getArgs()[0], (long) joinPoint.getArgs()[1], loggedInId))) {
            throw new NotMyCommentException();
        }
    }
}
