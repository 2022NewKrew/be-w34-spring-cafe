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
    public String registerReply(@PathVariable int articleId, String contents, HttpSession session)
        throws IllegalUserIdException, IllegalWriterException, IllegalTitleException, IllegalDateException {
        UserInfo sessionedUser = (UserInfo) session.getAttribute("sessionedUser");
        WriteReplyRequest writeReplyRequest = new WriteReplyRequest.Builder().articleId(articleId)
                                                                             .userId(sessionedUser.getUserId())
                                                                             .writer(sessionedUser.getName())
                                                                             .contents(contents)
                                                                             .build();

        writeReplyUseCase.writeReply(writeReplyRequest);
        return "redirect:/articles/" + articleId;
    }
}
