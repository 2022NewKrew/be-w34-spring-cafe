package com.kakao.cafe.qna.controller;

import com.kakao.cafe.qna.DTO.QuestionDTO;
import com.kakao.cafe.user.DTO.SignUpDTO;
import com.kakao.cafe.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@Controller
@RequiredArgsConstructor
public class QnaController {
    Logger logger = LoggerFactory.getLogger(com.kakao.cafe.user.controller.UserController.class);

    @PostMapping("/questions")
    public String SignUp(@ModelAttribute QuestionDTO newQuestion) {
        logger.info("a%s\n",newQuestion.getWriter());
        return "redirect:/";
    }
}
