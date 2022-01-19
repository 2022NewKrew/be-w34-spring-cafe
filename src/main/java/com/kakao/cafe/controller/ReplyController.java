package com.kakao.cafe.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("articles/{articleId}/replies")
public class ReplyController {

    @PutMapping
    public String createReply(@PathVariable int articleId, @ModelAttribute ReplyDto replyDto, HttpSession httpSession) {
        //
        return String.format("redirect:/articles/{}", articleId);
    }

    @DeleteMapping
    public String deleteReply(@PathVariable int articleId) {
        return "";
    }




}
