package com.kakao.cafe.controller;

import com.kakao.cafe.domain.dto.ReplyWriteDto;
import com.kakao.cafe.domain.model.User;
import com.kakao.cafe.service.ReplyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
@RequestMapping("/reply")
public class ReplyController {

    private final ReplyService replyService;

    @PostMapping("/{articleId}")
    public String postReply(HttpSession session, @Valid ReplyWriteDto replyWriteDto, @PathVariable String articleId){
        User sessionedUser = (User) session.getAttribute("sessionedUser");
        replyWriteDto.setArticleId(Integer.parseInt(articleId.trim()));
        replyWriteDto.setUserId(sessionedUser.getUserId());

        replyService.postReply(replyWriteDto);

        return "redirect:/article/" + articleId;
    }

    @DeleteMapping("/{articleId}/{replyId}")
    public String deleteReply(@PathVariable String articleId, @PathVariable String replyId){
        replyService.deleteReply(replyId);
        return "redirect:/article/" + articleId;
    }
}
