package com.kakao.cafe.util;

import org.springframework.ui.Model;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Map;

@ControllerAdvice
public class ErrorHandler {
    private static final Map<String, String> fieldMap;
    private static final String FIELD_ERROR_MESSAGE_FORMAT = "%s의 내용이 올바르지 않습니다. (%s)";

    static {
        /*
        Map<String,String> map = new HashMap<>();
        map.put("password","비밀번호");
        map.put("userId","닉네임");
        map.put("email","이메일");
        map.put("contents","내용");
        map.put("title","제목");
        fieldMap = Collections.unmodifiableMap(map);
        */
        //이 내용이 아래와 같이 치환됨
        fieldMap = Map.of("password", "비밀번호", "userId", "닉네임", "email", "이메일", "contents", "내용", "title", "제목");
    }

    @ExceptionHandler(Exception.class)
    public String handleException(Exception exception, Model model) {
        exception.printStackTrace();
        model.addAttribute(Constants.ERROR_MESSAGE_ATTRIBUTE_NAME, Constants.DEFAULT_ERROR_MESSAGE);

        return Constants.ERROR_PAGE_NAME;
    }

    @ExceptionHandler(BindException.class)
    public String handleBindException(BindException exception, Model model) {
        FieldError error = exception.getBindingResult().getFieldErrors().get(0);
        String field = error.getField();
        String message = String.format(FIELD_ERROR_MESSAGE_FORMAT, fieldMap.get(field), error.getDefaultMessage());
        model.addAttribute(Constants.ERROR_MESSAGE_ATTRIBUTE_NAME, message);

        return Constants.ERROR_PAGE_NAME;
    }

}
