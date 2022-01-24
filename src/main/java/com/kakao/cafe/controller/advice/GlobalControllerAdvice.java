package com.kakao.cafe.controller.advice;

import static com.kakao.cafe.controller.Constant.UNEXPECTED_EXCEPTION_MESSAGE;

import javax.naming.NoPermissionException;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@ControllerAdvice(basePackages = "com.kakao.cafe.controller")
public class GlobalControllerAdvice {

    private final Logger logger = LoggerFactory.getLogger(GlobalControllerAdvice.class);

    @ExceptionHandler(IllegalArgumentException.class)
    public String handleIllegalArgumentException(HttpServletRequest request,
            IllegalArgumentException exception, RedirectAttributes redirectAttributes) {
        logger.info("illegal argument exception, method : {}, url : {}, exception : {}",
                request.getMethod(),
                request.getRequestURI(), exception.getMessage());

        addCommonAttribute(request, exception, redirectAttributes);

        return RedirectUrl
                .getRedirectUrl(request.getMethod(), request.getRequestURI())
                .getRedirect();
    }

    @ExceptionHandler(NoPermissionException.class)
    public String handlePermissionException(HttpServletRequest request,
            NoPermissionException exception, RedirectAttributes redirectAttributes) {
        logger.info("permission exception, method : {}, url : {}, exceptionMsg : {}",
                request.getMethod(),
                request.getRequestURI(), exception.getMessage());

        addCommonAttribute(request, exception, redirectAttributes);

        return RedirectUrl
                .getRedirectUrl(request.getMethod(), request.getRequestURI())
                .getRedirect();
    }

    @ExceptionHandler(Exception.class)
    public String handleUnexpectedException(HttpServletRequest request, Exception exception,
            RedirectAttributes redirectAttributes) {
        logger.info("unexpected Exception, method : {}, url : {}, exceptionMsg : {}",
                request.getMethod(),
                request.getRequestURI(), exception.getMessage());

        addCommonAttribute(request, exception, redirectAttributes);

        return RedirectUrl
                .DEFAULT_REDIRECT_URL
                .getRedirect();
    }

    private void addCommonAttribute(HttpServletRequest request, Exception exception,
            RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("errorExist", true);
        redirectAttributes.addFlashAttribute("errorMsg", exception.getMessage());
        if (request.getParameter("id") != null) {
            redirectAttributes.addAttribute("id", request.getParameter("id"));
        }
    }
}
