package com.kakao.cafe.controller;

import com.kakao.cafe.aop.annotation.LoginCheckAnnotation;
import com.kakao.cafe.domain.article.Text;
import com.kakao.cafe.domain.member.Member;
import com.kakao.cafe.dto.PostCommentDto;
import com.kakao.cafe.exception.ErrorMessages;
import com.kakao.cafe.exception.NotEnoughFieldException;
import com.kakao.cafe.service.article.CommentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
@Slf4j
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/questions/{articleId}/comment")
    @LoginCheckAnnotation
    public String postComment(@PathVariable("articleId") Long articleId, @Valid PostCommentDto commentDto, HttpSession session, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            log.info("들어옴");
            throw new NotEnoughFieldException(ErrorMessages.NOT_ENOUGH_FIELD);
        }
        Member loginMember = (Member) session.getAttribute("loginMember");
        commentService.postComment(articleId, loginMember, new Text(commentDto.getText()));
        return "redirect:/questions/" + articleId;
    }
}
