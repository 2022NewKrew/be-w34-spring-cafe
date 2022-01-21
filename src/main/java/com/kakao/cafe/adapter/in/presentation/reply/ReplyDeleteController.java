package com.kakao.cafe.adapter.in.presentation.reply;

import com.kakao.cafe.application.reply.port.in.DeleteReplyUseCase;
import com.kakao.cafe.application.user.dto.UserInfo;
import com.kakao.cafe.domain.user.exceptions.UnauthenticatedUserException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ReplyDeleteController {

    private final DeleteReplyUseCase deleteReplyUseCase;

    public ReplyDeleteController(DeleteReplyUseCase deleteReplyUseCase) {
        this.deleteReplyUseCase = deleteReplyUseCase;
    }

    @DeleteMapping("/articles/{articleId}/replies/{id}")
    public String deleteReply(@PathVariable int articleId, @PathVariable int id, @RequestParam String userId, @RequestAttribute UserInfo sessionedUser)
        throws UnauthenticatedUserException {
        deleteReplyUseCase.delete(id, userId, sessionedUser);
        return "redirect:/articles/" + articleId;
    }

    @GetMapping("/articles/{articleId}/replies/delete")
    public String deleteAllRepliesInArticle(@PathVariable int articleId, @RequestParam String userId, @RequestAttribute UserInfo sessionedUser)
        throws UnauthenticatedUserException {
        deleteReplyUseCase.deleteAllRepliesInArticle(articleId, userId, sessionedUser);
        return "redirect:/";
    }
}
