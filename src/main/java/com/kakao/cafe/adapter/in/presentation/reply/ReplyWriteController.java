package com.kakao.cafe.adapter.in.presentation.reply;

import com.kakao.cafe.application.reply.dto.WriteReplyRequest;
import com.kakao.cafe.application.reply.port.in.WriteReplyUseCase;
import com.kakao.cafe.application.user.dto.UserInfo;
import com.kakao.cafe.domain.article.exceptions.IllegalDateException;
import com.kakao.cafe.domain.article.exceptions.IllegalTitleException;
import com.kakao.cafe.domain.article.exceptions.IllegalWriterException;
import com.kakao.cafe.domain.user.exceptions.IllegalUserIdException;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ReplyWriteController {

    private final WriteReplyUseCase writeReplyUseCase;

    public ReplyWriteController(WriteReplyUseCase writeReplyUseCase) {
        this.writeReplyUseCase = writeReplyUseCase;
    }

    @PostMapping("/articles/{articleId}/replies")
    public String registerReply(@PathVariable int articleId, WriteReplyRequest writeReplyRequest, HttpSession session)
        throws IllegalUserIdException, IllegalWriterException, IllegalTitleException, IllegalDateException {
        UserInfo sessionedUser = (UserInfo) session.getAttribute("sessionedUser");
        writeReplyRequest.setArticleId(articleId);
        writeReplyRequest.setUserId(sessionedUser.getUserId());
        writeReplyRequest.setWriter(sessionedUser.getName());
        writeReplyUseCase.writeReply(writeReplyRequest);
        return "redirect:/articles/" + articleId;
    }
}
