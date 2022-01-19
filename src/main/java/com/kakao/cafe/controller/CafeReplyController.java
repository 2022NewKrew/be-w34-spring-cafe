package com.kakao.cafe.controller;

import com.kakao.cafe.model.Reply;
import com.kakao.cafe.service.CafeReplyService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/reply")
public class CafeReplyController {

    CafeReplyService cafeCommentService;

    public CafeReplyController(CafeReplyService cafeCommentService) {
        this.cafeCommentService = cafeCommentService;
    }

    @PostMapping()
    String submitComment(Reply reply){
        cafeCommentService.submitReply(reply);
        return "";
    }
    @DeleteMapping()
    String deleteComment(int replyId) {
        cafeCommentService.deleteReply(replyId);
        return "";
    }

}
