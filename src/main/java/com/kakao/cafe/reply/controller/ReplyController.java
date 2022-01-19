package com.kakao.cafe.reply.controller;

import com.kakao.cafe.reply.dto.request.ReplyCreateRequest;
import com.kakao.cafe.reply.service.ReplyService;
import com.kakao.cafe.user.dto.response.UserInfoResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import static com.kakao.cafe.common.util.KakaoCafeUtil.getUserInfoInSession;

@Controller
@Slf4j
@RequiredArgsConstructor
public class ReplyController {

    private final ReplyService replyService;

    @PostMapping("/articles/{articleId}/replies")
    public String createReply(HttpSession session, @Valid ReplyCreateRequest req, @PathVariable("articleId") Long articleId) {
        log.info("[POST] /articles/{}/replies - 댓글 생성 요청", articleId);
        UserInfoResponse user = getUserInfoInSession(session);

        this.replyService.createReply(req, user.getId(), articleId);

        return "redirect:/articles/{articleId}";
    }

    @DeleteMapping("/articles/{articleId}/replies/{replyId}")
    public String deleteReply(HttpSession session, @PathVariable("articleId") Long articleId, @PathVariable("replyId") Long replyId) {
        log.info("[DELETE] /articles/{}/replies/{} - 댓글 삭제 요청", articleId, replyId);
        UserInfoResponse user = getUserInfoInSession(session);

        this.replyService.validateUser(user.getId(), replyId);

        this.replyService.deleteReply(replyId);

        return "redirect:/articles/{articleId}";
    }
}
