package com.kakao.cafe.adapter.in.presentation.article;

import com.kakao.cafe.application.article.dto.WriteArticleRequest;
import com.kakao.cafe.application.article.port.in.WriteArticleUseCase;
import com.kakao.cafe.application.user.dto.UserInfo;
import com.kakao.cafe.domain.article.exceptions.IllegalDateException;
import com.kakao.cafe.domain.article.exceptions.IllegalTitleException;
import com.kakao.cafe.domain.article.exceptions.IllegalWriterException;
import com.kakao.cafe.domain.user.exceptions.IllegalUserIdException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;

@Controller
public class ArticleWriteController {

    private final WriteArticleUseCase writeArticleUseCase;

    public ArticleWriteController(WriteArticleUseCase writeArticleUseCase) {
        this.writeArticleUseCase = writeArticleUseCase;
    }

    @PostMapping("/articles/form")
    public String registerArticle(String title, String contents, @RequestAttribute UserInfo sessionedUser)
        throws IllegalWriterException, IllegalTitleException, IllegalDateException, IllegalUserIdException {
        WriteArticleRequest writeArticleRequest = new WriteArticleRequest.Builder().userId(sessionedUser.getUserId())
                                                                                   .writer(sessionedUser.getName())
                                                                                   .title(title)
                                                                                   .contents(contents)
                                                                                   .build();

        writeArticleUseCase.writeArticle(writeArticleRequest);
        return "redirect:/";
    }
}
