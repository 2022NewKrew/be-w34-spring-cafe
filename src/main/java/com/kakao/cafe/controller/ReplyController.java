package com.kakao.cafe.controller;

import com.kakao.cafe.Dto.Login.LoginAuthDto;
import com.kakao.cafe.Service.ReplyService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequiredArgsConstructor
@RequestMapping("/qna")
public class ReplyController {

    private final Logger logger = LoggerFactory.getLogger(ReplyController.class);
    private final ReplyService replyService;

    @PostMapping("/{postId}/reply")
    public String createReply(@PathVariable int postId, String comment, HttpSession session) {
        logger.info("[POST] 댓글 작성 / comment:{}", comment);

        LoginAuthDto authInfo = (LoginAuthDto) session.getAttribute("authInfo");
        replyService.createReply(postId, comment, authInfo);

        return "redirect:/qna/{postId}";
    }

    @DeleteMapping("/{postId}/reply/{replyId}")
    public String deleteReply(@PathVariable int replyId, HttpSession session) {
        logger.info("[DELETE] 댓글 삭제");

        LoginAuthDto authInfo = (LoginAuthDto) session.getAttribute("authInfo");
        replyService.deleteById(replyId, authInfo);

        return "redirect:/qna/{postId}";
    }
}
