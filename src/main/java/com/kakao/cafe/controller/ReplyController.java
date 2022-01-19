package com.kakao.cafe.controller;

import com.kakao.cafe.dto.reply.ReplyDto;
import com.kakao.cafe.dto.user.UserSessionDto;
import com.kakao.cafe.exception.UserMismatchException;
import com.kakao.cafe.service.ReplyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/reply")
@Slf4j
@RequiredArgsConstructor
public class ReplyController {

    private final ReplyService replyService;

    @PostMapping("/{articleId}/comment")
    public String comment(@PathVariable int articleId, ReplyDto replyDto, HttpSession session) {
        UserSessionDto sessionedUser = (UserSessionDto) session.getAttribute("sessionedUser");
        replyDto.setArticleId(articleId);
        replyDto.setUserId(sessionedUser.getUserId());
        log.info("댓글 : {}", replyDto);
        replyService.addReply(replyDto);
        return "redirect:/articles/" + articleId;
    }

    @DeleteMapping("/{replyId}/delete")
    public String deleteReply(@PathVariable int replyId, ReplyDto replyDto, HttpSession session) throws UserMismatchException {
        UserSessionDto sessionedUser = (UserSessionDto) session.getAttribute("sessionedUser");
        log.info("댓글 아이디 : {}",replyId);
        if (!replyDto.getUserId().equals(sessionedUser.getUserId())) {
            throw new UserMismatchException("다른 사용자의 댓글을 삭제할 수 없습니다.");
        }
        replyService.deleteReply(replyId);
        return "redirect:/articles/" + replyDto.getArticleId();
    }
}
