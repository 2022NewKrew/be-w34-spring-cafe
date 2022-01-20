package com.kakao.cafe.adapter.in.presentation.article;

import com.kakao.cafe.application.article.dto.WriteRequest;
import com.kakao.cafe.application.article.port.in.WriteArticleUseCase;
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
public class ArticleReplyWriteController {

    private final WriteArticleUseCase writeArticleUseCase;
    private final WriteReplyUseCase writeReplyUseCase;

    public ArticleReplyWriteController(WriteArticleUseCase writeArticleUseCase, WriteReplyUseCase writeReplyUseCase) {
        this.writeArticleUseCase = writeArticleUseCase;
        this.writeReplyUseCase = writeReplyUseCase;
    }

    @PostMapping("/articles")
    public String registerArticle(WriteRequest writeRequest, HttpSession session)
        throws IllegalWriterException, IllegalTitleException, IllegalDateException, IllegalUserIdException {
        UserInfo sessionedUser = (UserInfo) session.getAttribute("sessionedUser");
        writeRequest.setWriter(sessionedUser.getName());
        writeRequest.setUserId(sessionedUser.getUserId());
        writeArticleUseCase.writeArticle(writeRequest);
        return "redirect:/";
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
