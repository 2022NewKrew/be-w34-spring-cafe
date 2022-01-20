package com.kakao.cafe.article.adapter.in.web;

import com.kakao.cafe.article.application.port.in.ArticleUpdateForm;
import com.kakao.cafe.article.application.port.in.ArticleUpdateUseCase;
import com.kakao.cafe.common.exception.ArticleUpdateException;
import com.kakao.cafe.common.meta.SessionData;
import com.kakao.cafe.common.meta.URLPath;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
@RequiredArgsConstructor
public class ArticleUpdateController {
    private final ArticleUpdateUseCase articleUpdateUseCase;

    @GetMapping("/articles/{articleId}/updateForm")
    public ModelAndView showArticleUpdateForm(@PathVariable("articleId") Long articleId,
                                              HttpSession session) throws ArticleUpdateException {
        final Object userIdObj = session.getAttribute(SessionData.USER_KEY);
        if (userIdObj == null) throw new ArticleUpdateException();
        final Long userId = (Long) userIdObj;
        ArticleUpdateForm articleUpdateForm = articleUpdateUseCase.findArticleUpdateForm(articleId, userId);
        return new ModelAndView("/post/updateForm")
                .addObject("article", articleUpdateForm);
    }

    @PutMapping("/articles/{articleId}")
    public ModelAndView updateArticle(@PathVariable("articleId") Long articleId,
                                      @RequestParam("title") String title,
                                      @RequestParam("contents") String contents,
                                      HttpSession session) throws ArticleUpdateException {
        Object writerNameObj = session.getAttribute(SessionData.USER_NAME);
        if (writerNameObj == null) throw new ArticleUpdateException();
        String writerName = (String) writerNameObj;
        articleUpdateUseCase.updateArticle(articleId, title, writerName, contents);
        return new ModelAndView(URLPath.INDEX.getPath());
    }
}
