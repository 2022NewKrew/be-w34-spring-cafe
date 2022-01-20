package com.kakao.cafe.controller;

import com.kakao.cafe.config.auth.LoginUser;
import com.kakao.cafe.dto.RequestReplyDto;
import com.kakao.cafe.dto.SessionUser;
import com.kakao.cafe.service.ReplyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

@Slf4j
@RequiredArgsConstructor
@Controller
public class ReplyController {

    private final ReplyService replyService;

    /*
     * 댓글 작성
     */
    @PostMapping("/articles/{articleId}/replies")
    public String addReply(@PathVariable long articleId, @ModelAttribute RequestReplyDto replyDto, @LoginUser SessionUser user) {
        replyService.createReply(user.getId(), articleId, replyDto);
        return "redirect:/articles/{articleId}";
    }

    /*
     * 댓글 수정
     */
    @PutMapping("/articles/{articleId}/replies/{id}")
    public String editReply(@PathVariable long articleId, @PathVariable long id, @ModelAttribute RequestReplyDto replyDto, @LoginUser SessionUser user) {
        long authorId = replyService.getAuthorIdOfReply(id);
        if (user.getId() != authorId){
            throw new HttpClientErrorException(HttpStatus.FORBIDDEN);
        }
        replyService.updateReply(id, replyDto);
        return "redirect:/articles/{articleId}";
    }

    /*
     * 댓글 삭제
     */
    @DeleteMapping("/articles/{articleId}/replies/{id}")
    public String deleteReply(@PathVariable long articleId, @PathVariable long id, @LoginUser SessionUser user){
        long authorId = replyService.getAuthorIdOfReply(id);
        if (user.getId() != authorId){
            throw new HttpClientErrorException(HttpStatus.FORBIDDEN);
        }
        replyService.deleteReply(id);
        return "redirect:/articles/{articleId}";
    }
}
