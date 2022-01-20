package com.kakao.cafe.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class GeneralExceptionHandler {
    private final Logger log = LoggerFactory.getLogger(getClass());

    /**
     * Authentication 객체가 필요한 권한을 보유하지 않은 경우 발생합
     */
    @ExceptionHandler(AccessDeniedException.class)
    protected String handleAccessDeniedException(AccessDeniedException e, Model model) {
        log.error(e.getMessage());
        model.addAttribute("ApiError", new ApiError(e, HttpStatus.FORBIDDEN));
        return "error/errorPage";
    }

    /**
     * 비즈니스 로직 수행 도중, 사용자의 요청 파라미터가 적절하지 않을 때 발생
     * 비즈니스 로직 수행 도중, 해당 도메인 객체의 상태가 로직을 수행할 수 없을 때 발생
     */
    @ExceptionHandler({
            IllegalStateException.class,
            IllegalArgumentException.class
    })
    protected String handleIllegalStatementException(Exception e, Model model) {
        log.error(e.getMessage());
        model.addAttribute("ApiError", new ApiError(e, HttpStatus.BAD_REQUEST));
        return "error/errorPage";
    }

    /**
     * 비즈니스 로직 수행 도중, 객체를 찾을 수 없는 상태일 때 발생. 이 때 404 status code와 함께 반환한다.
     */
    @ExceptionHandler({
            UserNotFoundException.class,
            ArticleNotFoundException.class
    })
    protected String handleNotFoundException(Exception e, Model model) {
        log.error(e.getMessage());
        model.addAttribute("ApiError", new ApiError(e, HttpStatus.NOT_FOUND));
        return "error/errorPage";
    }

    /**
     * 여기서 작성하지 않은 다른 모든 예외에 대해 처리한다. 이 때 500 status code와 함께 반환한다.
     */
    @ExceptionHandler(Exception.class)
    protected String handleException(Exception e, Model model) {
        e.printStackTrace();
        model.addAttribute("ApiError", new ApiError(e, HttpStatus.INTERNAL_SERVER_ERROR));
        return "error/errorPage";
    }
}
