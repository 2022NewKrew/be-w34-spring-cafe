package com.kakao.cafe.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/qna")
public class QnAController {

    @GetMapping("/form")
    public String qnaForm() {
        return "qna/form";
    }

    @GetMapping("/show")
    public String qnaShow() {
        return "qna/show";
    }

}
