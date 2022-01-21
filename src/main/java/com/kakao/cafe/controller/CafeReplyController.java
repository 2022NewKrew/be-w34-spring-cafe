package com.kakao.cafe.controller;

import com.kakao.cafe.annotation.LoginUser;
import com.kakao.cafe.model.Reply;
import com.kakao.cafe.model.User;
import com.kakao.cafe.service.CafeReplyService;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/replies")
public class CafeReplyController {

    CafeReplyService cafeCommentService;
    public CafeReplyController(CafeReplyService cafeCommentService) {
        this.cafeCommentService = cafeCommentService;
    }

    private static final String REDIRECT_PREFIX = "redirect:";
    private static final String REPLY_REDIRECT_SUBMIT_SUCCESS = REDIRECT_PREFIX+"/posts/content/";
    private static final String REPLY_REDIRECT_SUBMIT_FAIL = REDIRECT_PREFIX+"/error";
    private static final String REPLY_REDIRECT_DELETE_SUCCESS = REDIRECT_PREFIX+"/posts/content/";
    private static final String REPLY_REDIRECT_DELETE_FAIL = REDIRECT_PREFIX+"/error";

    @PostMapping("/{postId}")
    String submitReply(@LoginUser String loginUser, @NonNull @PathVariable("postId") int postId, @NonNull Reply reply){
        if(loginUser != null) {
            reply.setUserId(loginUser);
            if (cafeCommentService.submitReply(reply)) {
                return REPLY_REDIRECT_SUBMIT_FAIL;
            }
        }
        return REPLY_REDIRECT_SUBMIT_SUCCESS + postId;
    }

    @DeleteMapping("/{postId}/{replyId}")
    String deleteReply(@LoginUser String loginUser, @NonNull @PathVariable("postId") int postId, @NonNull @PathVariable("replyId") int replyId) {
        if(loginUser != null && cafeCommentService.deleteReply(loginUser, replyId)) {
            return REPLY_REDIRECT_DELETE_SUCCESS + postId;
        }
        return REPLY_REDIRECT_DELETE_FAIL;
    }
}
