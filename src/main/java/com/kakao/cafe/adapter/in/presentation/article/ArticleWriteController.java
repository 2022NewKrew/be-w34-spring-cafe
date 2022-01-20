package com.kakao.cafe.adapter.in.presentation.article;

import com.kakao.cafe.application.article.dto.WriteRequest;
import com.kakao.cafe.application.article.port.in.WriteArticleUseCase;
import com.kakao.cafe.application.user.dto.UserInfo;
import com.kakao.cafe.domain.article.exceptions.IllegalDateException;
import com.kakao.cafe.domain.article.exceptions.IllegalTitleException;
import com.kakao.cafe.domain.article.exceptions.IllegalWriterException;
import com.kakao.cafe.domain.user.exceptions.IllegalUserIdException;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ArticleWriteController {

    private final WriteArticleUseCase writeArticleUseCase;

    public ArticleWriteController(WriteArticleUseCase writeArticleUseCase) {
        this.writeArticleUseCase = writeArticleUseCase;
    }

    @PostMapping("/articles")
    public String registerArticle(String title, String contents, HttpSession session)
        throws IllegalWriterException, IllegalTitleException, IllegalDateException, IllegalUserIdException {
        UserInfo sessionedUser = (UserInfo) session.getAttribute("sessionedUser");
        WriteRequest writeRequest = new WriteRequest.Builder().userId(sessionedUser.getUserId())
                                                              .writer(sessionedUser.getName())
                                                              .title(title)
                                                              .contents(contents)
                                                              .build();

        writeArticleUseCase.writeArticle(writeRequest);
        return "redirect:/";
    }
}
