package com.kakao.cafe.adapter.in.presentation.article;

import com.kakao.cafe.application.reply.dto.WriteReplyRequest;
import com.kakao.cafe.application.reply.port.in.WriteReplyUseCase;
import com.kakao.cafe.application.user.dto.UserInfo;
import com.kakao.cafe.domain.article.exceptions.IllegalDateException;
import com.kakao.cafe.domain.article.exceptions.IllegalTitleException;
import com.kakao.cafe.domain.article.exceptions.IllegalWriterException;
import com.kakao.cafe.domain.user.exceptions.IllegalUserIdException;
import com.kakao.cafe.domain.user.exceptions.UnauthenticatedUserException;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ReplyWriteController {

    private final WriteReplyUseCase writeReplyUseCase;

    public ReplyWriteController(WriteReplyUseCase writeReplyUseCase) {
        this.writeReplyUseCase = writeReplyUseCase;
    }

    @PostMapping("/articles/{articleId}/replys")
    public String register(@PathVariable int articleId, @RequestParam String userId, WriteReplyRequest writeReplyRequest, HttpSession session)
        throws UnauthenticatedUserException, IllegalUserIdException, IllegalWriterException, IllegalTitleException, IllegalDateException {
        UserInfo sessionedUser = (UserInfo) session.getAttribute("sessionedUser");
        if (!sessionedUser.getUserId().equals(userId)) {
            throw new UnauthenticatedUserException("인증 오류");
        }
        writeReplyRequest.setArticleId(articleId);
        writeReplyRequest.setUserId(sessionedUser.getUserId());
        writeReplyRequest.setWriter(sessionedUser.getName());
        writeReplyUseCase.writeReply(writeReplyRequest);
        return "redirect:/articles/" + articleId;
    }
}
