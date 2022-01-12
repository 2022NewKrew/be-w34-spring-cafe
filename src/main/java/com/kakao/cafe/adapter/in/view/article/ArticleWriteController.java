package com.kakao.cafe.adapter.in.view.article;

import com.kakao.cafe.application.article.dto.WriteRequest;
import com.kakao.cafe.application.article.port.in.WriteArticleUseCase;
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
    public String register(WriteRequest writeRequest) {
        log.info("{} wrote an article", writeRequest.getWriter());
        writeArticleUseCase.writeArticle(writeRequest);
        return "redirect:/";
    }
}
