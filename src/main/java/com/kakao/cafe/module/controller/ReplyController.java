package com.kakao.cafe.module.controller;

import com.kakao.cafe.module.service.InfraService;
import com.kakao.cafe.module.service.ReplyService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.HttpSessionRequiredException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

import static com.kakao.cafe.module.model.dto.ReplyDtos.*;
import static com.kakao.cafe.module.model.dto.UserDtos.*;

@Controller
@RequestMapping("/articles")
@RequiredArgsConstructor
public class ReplyController {

    private static final Logger logger = LoggerFactory.getLogger(ArticleController.class);
    private final ReplyService replyService;
    private final InfraService infraService;

    @PostMapping("/{articleId}/reply")
    public String postReply(@PathVariable Long articleId, ReplyPostDto replyPostDto, HttpSession session) throws HttpSessionRequiredException {
        UserDto userDto = infraService.retrieveUserSession(session);
        replyService.postReply(articleId, userDto.getId(), replyPostDto);
        logger.info("Post Reply at Article : {}", articleId);
        return "redirect:/articles/{articleId}";
    }

    @DeleteMapping("/{articleId}/reply/{id}")
    public String deleteReply(@PathVariable Long articleId, @PathVariable Long id, HttpSession session) throws HttpSessionRequiredException {
        infraService.validateSession(session, replyService.getReply(id).getAuthorId());
        replyService.deleteReply(articleId, id);
        logger.info("Delete Reply at Article : {}", articleId);
        return "redirect:/articles/{articleId}";
    }
}
