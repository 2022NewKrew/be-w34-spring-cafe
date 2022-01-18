package com.kakao.cafe.article.adapter.in.web;

import com.kakao.cafe.article.application.port.in.ArticleUpdateUseCase;
import com.kakao.cafe.article.application.port.in.ArticleUpdateCommand;
import com.kakao.cafe.common.exception.ArticleUpdateException;
import com.kakao.cafe.common.meta.SessionData;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
@RequiredArgsConstructor
public class ArticleUpdateController {
    private final ArticleUpdateUseCase articleUpdateUseCase;

    @GetMapping("/articles/{{articleId}}/updateForm")
    public ModelAndView articleUpdate(@PathVariable("articleId") Long articleId,
                                      HttpSession session) throws ArticleUpdateException {
        final Object userIdObj = session.getAttribute(SessionData.USER_KEY);
        if (userIdObj == null) throw new ArticleUpdateException();
        final Long userId = (Long) userIdObj;
        ArticleUpdateCommand articleUpdateCommand = articleUpdateUseCase.updateArticle(articleId, userId);
        return new ModelAndView("/post/updateForm")
                .addObject("article", articleUpdateCommand);
    }
}
