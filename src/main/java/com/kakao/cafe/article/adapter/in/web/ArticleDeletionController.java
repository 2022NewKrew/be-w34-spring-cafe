package com.kakao.cafe.article.adapter.in.web;

import com.kakao.cafe.article.application.port.in.ArticleDeletionUseCase;
import com.kakao.cafe.common.exception.DeletionException;
import com.kakao.cafe.common.meta.URLPath;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.SessionAttribute;

@Controller
@RequiredArgsConstructor
public class ArticleDeletionController {
    private final ArticleDeletionUseCase articleDeletionUseCase;

    @DeleteMapping("articles/{articleId}/delete")
    public String deleteArticle(@PathVariable("articleId") String articleId,
                                @SessionAttribute("userKey") Long userKey) throws DeletionException {
        articleDeletionUseCase.deleteArticle(articleId, userKey);
        return URLPath.INDEX.getRedirectPath();
    }
}
