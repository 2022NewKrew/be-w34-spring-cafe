package com.kakao.cafe.adapter.in.presentation.article;

import com.kakao.cafe.application.article.dto.UpdateArticleRequest;
import com.kakao.cafe.application.article.port.in.UpdateArticleUseCase;
import com.kakao.cafe.application.user.dto.UserInfo;
import com.kakao.cafe.domain.article.exceptions.IllegalDateException;
import com.kakao.cafe.domain.article.exceptions.IllegalTitleException;
import com.kakao.cafe.domain.article.exceptions.IllegalWriterException;
import com.kakao.cafe.domain.user.exceptions.IllegalUserIdException;
import com.kakao.cafe.domain.user.exceptions.UnauthenticatedUserException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ArticleUpdateController {

    private final UpdateArticleUseCase updateArticleUseCase;

    public ArticleUpdateController(UpdateArticleUseCase updateArticleUseCase) {
        this.updateArticleUseCase = updateArticleUseCase;
    }


    @PutMapping("/articles/{id}/form")
    public String update(@PathVariable int id, @RequestParam String userId, String title, String contents, @RequestAttribute UserInfo sessionedUser)
        throws IllegalWriterException, IllegalTitleException, IllegalDateException, IllegalUserIdException, UnauthenticatedUserException {
        UpdateArticleRequest updateArticleRequest = new UpdateArticleRequest.Builder().id(id)
                                                                                      .userId(sessionedUser.getUserId())
                                                                                      .writer(sessionedUser.getName())
                                                                                      .title(title)
                                                                                      .contents(contents)
                                                                                      .build();
        updateArticleUseCase.updateArticle(updateArticleRequest, userId, sessionedUser);
        return "redirect:/";
    }
}
