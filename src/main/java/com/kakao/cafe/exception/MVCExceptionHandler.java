package com.kakao.cafe.exception;

import com.kakao.cafe.web.dto.ErrorDTO;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice("com.kakao.cafe.web.controller.mvc")
public class MVCExceptionHandler {

  @ExceptionHandler(Exception.class)
  private String handleException(Exception e, Model model) {
    ErrorConst error = ErrorConst.findBy(e);
    e.printStackTrace();
    model.addAttribute("error", ErrorDTO.of(error));
    return "error_custom";
  }

}
