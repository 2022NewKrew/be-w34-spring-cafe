package com.kakao.cafe.controller;

import com.kakao.cafe.dto.ReplyRequestDto;
import com.kakao.cafe.entity.User;
import com.kakao.cafe.service.ReplyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;

@Controller
public class ReplyController {
    private final ReplyService replyService;
    private final Logger logger = LoggerFactory.getLogger(ReplyController.class);

    public ReplyController(ReplyService replyService) {
        this.replyService = replyService;
    }

    @PostMapping("/articles/{index}")
    public String createReply(@PathVariable String index, ReplyRequestDto replyRequestDto, HttpSession httpSession) {
        User sessionedUser = (User) httpSession.getAttribute("sessionedUser");
        if(sessionedUser == null)
            return "redirect:/user/login";

        replyService.createRelpy(index, replyRequestDto, sessionedUser);
        return "redirect:/articles/{index}";
    }

    @DeleteMapping("/articles/{articleId}/reply/{replyId}")
    public String deleteReply(@PathVariable String articleId, @PathVariable String replyId, HttpSession httpSession) {
        // 로그인 안했거나 작성자가 일치하지 않으면 삭제 불가
        if (!replyService.isDeletable(replyId, httpSession))
            return "qna/deleteImpossible";

        replyService.deleteById(replyId);
        logger.info("delete reply no :" + replyId);
        return "redirect:/articles/{articleId}";
    }

}
