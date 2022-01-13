package com.kakao.cafe.util;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.ui.Model;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Map;

@ControllerAdvice
public class ErrorHandler {
    private static final Map<String, String> fieldMap;
    private static final String DEFAULT_ERROR_MESSAGE = "서버에서 에러가 발생했습니다. 잠시후 다시 시도해주세요.";
    private static final String DB_INTEGRITY_ERROR_MESSAGE = "사용자를 생성후 글을 작성해 주세요.";
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
        model.addAttribute(Constants.ERROR_MESSAGE_ATTRIBUTE_NAME, DEFAULT_ERROR_MESSAGE);

        return Constants.ERROR_PAGE_NAME;
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public String handleDataIntegrityViolationException(DataIntegrityViolationException exception, Model model) {
        model.addAttribute(Constants.ERROR_MESSAGE_ATTRIBUTE_NAME, DB_INTEGRITY_ERROR_MESSAGE);

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
