package com.kakao.cafe.adapter.in.presentation.reply;

import com.kakao.cafe.application.reply.dto.WriteReplyRequest;
import com.kakao.cafe.application.reply.port.in.WriteReplyUseCase;
import com.kakao.cafe.application.user.dto.UserInfo;
import com.kakao.cafe.domain.article.exceptions.IllegalDateException;
import com.kakao.cafe.domain.article.exceptions.IllegalTitleException;
import com.kakao.cafe.domain.article.exceptions.IllegalWriterException;
import com.kakao.cafe.domain.user.exceptions.IllegalUserIdException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;

@Controller
public class ReplyWriteController {

    private final WriteReplyUseCase writeReplyUseCase;

    public ReplyWriteController(WriteReplyUseCase writeReplyUseCase) {
        this.writeReplyUseCase = writeReplyUseCase;
    }

    @PostMapping("/articles/{articleId}/replies")
    public String registerReply(@PathVariable int articleId, String contents, @RequestAttribute UserInfo sessionedUser)
        throws IllegalUserIdException, IllegalWriterException, IllegalTitleException, IllegalDateException {
        WriteReplyRequest writeReplyRequest = new WriteReplyRequest.Builder().articleId(articleId)
                                                                             .userId(sessionedUser.getUserId())
                                                                             .writer(sessionedUser.getName())
                                                                             .contents(contents)
                                                                             .build();

        writeReplyUseCase.writeReply(writeReplyRequest);
        return "redirect:/articles/" + articleId;
    }
}
