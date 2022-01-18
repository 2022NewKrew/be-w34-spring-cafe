package com.kakao.cafe.adapter.in.presentation.article;

import com.kakao.cafe.application.article.dto.WriteRequest;
import com.kakao.cafe.application.article.port.in.WriteArticleUseCase;
import com.kakao.cafe.domain.article.exceptions.IllegalDateException;
import com.kakao.cafe.domain.article.exceptions.IllegalTitleException;
import com.kakao.cafe.domain.article.exceptions.IllegalWriterException;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ArticleWriteController {

    private static final Logger log = LoggerFactory.getLogger(ArticleWriteController.class);

    private final WriteArticleUseCase writeArticleUseCase;

    public ArticleWriteController(WriteArticleUseCase writeArticleUseCase) {
        this.writeArticleUseCase = writeArticleUseCase;
    }

    @PostMapping("/articles")
    public String register(HttpServletRequest request, WriteRequest writeRequest)
        throws IllegalWriterException, IllegalTitleException, IllegalDateException {
        log.info("[{}]{} required writing an article", request.getRequestURI(), writeRequest.getWriter());
        writeArticleUseCase.writeArticle(writeRequest);
        log.info("[{}]Success {} writing the article", request.getRequestURI(), writeRequest.getWriter());
        return "redirect:/";
    }
}
