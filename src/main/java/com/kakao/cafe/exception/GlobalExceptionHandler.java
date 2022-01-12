package com.kakao.cafe.exception;

import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.ModelAndView;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final String ERROR_VIEW = "errors/error";
    private final ModelAndView mv = new ModelAndView(ERROR_VIEW);

    Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(CustomException.class)
    public ModelAndView handleUserIdDuplicationException(HttpServletRequest request, CustomException e) {
        logger.error("handleUserIdDuplicationException status: {}", e.getErrorCode().getStatus());
        logger.error("handleUserIdDuplicationException errorCode: {}", e.getErrorCode().getErrorCode());
        logger.error("handleUserIdDuplicationException message: {}", e.getErrorCode().getMessage());

        Map<String, Object> model = mv.getModel();

        ErrorResponse response = new ErrorResponse(e.getErrorCode());
        model.put("response", response);

        String referer = request.getHeader("referer");
        model.put("referer", referer);

        return mv;
    }

    @ExceptionHandler(Exception.class)
    public ModelAndView handleException(HttpServletRequest request, Exception e) {
        logger.error("handleException message: {}", e.getMessage());
        logger.error("handleException message: {}", e.getClass());

        Map<String, Object> model = mv.getModel();

        ErrorResponse response = new ErrorResponse(ErrorCode.INTER_SERVER_ERROR);
        model.put("response", response);

        String referer = request.getHeader("referer");
        model.put("referer", referer);

        return mv;
    }
}
