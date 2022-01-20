package com.kakao.cafe.adapter.in.presentation.reply;

import com.kakao.cafe.application.reply.port.in.DeleteReplyUseCase;
import com.kakao.cafe.application.user.dto.UserInfo;
import com.kakao.cafe.domain.user.exceptions.UnauthenticatedUserException;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ReplyDeleteController {

    private final DeleteReplyUseCase deleteReplyUseCase;

    public ReplyDeleteController(DeleteReplyUseCase deleteReplyUseCase) {
        this.deleteReplyUseCase = deleteReplyUseCase;
    }

    @DeleteMapping("/articles/{articleId}/replies/{id}")
    public String deleteReply(@PathVariable int articleId, @PathVariable int id, @RequestParam String userId, HttpSession session)
        throws UnauthenticatedUserException {
        UserInfo sessionedUser = (UserInfo) session.getAttribute("sessionedUser");
        deleteReplyUseCase.delete(id, userId, sessionedUser);
        return "redirect:/articles/" + articleId;
    }
}
