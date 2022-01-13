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
    public ModelAndView handleCustomException(HttpServletRequest request, CustomException e) {
        logger.error("CustomException status: {}", e.getErrorCode().getStatus());
        logger.error("CustomException errorCode: {}", e.getErrorCode().getErrorCode());
        logger.error("CustomException message: {}", e.getErrorCode().getMessage());

        setModelAndView(request, e.getErrorCode());

        return mv;
    }

    private void setModelAndView(HttpServletRequest request, ErrorCode errorCode) {
        Map<String, Object> model = mv.getModel();
        ErrorResponse response = new ErrorResponse(errorCode);
        String referer = request.getHeader("referer");
        model.put("response", response);
        model.put("referer", referer);
    }
}
