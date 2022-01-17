package com.kakao.cafe.article.adapter.in;

import com.kakao.cafe.article.application.port.in.WriteArticleDto;
import com.kakao.cafe.article.application.port.in.WriteArticleUseCase;
import com.kakao.cafe.article.domain.ArticleId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class WriteArticleController {

    private final WriteArticleUseCase writeArticleUseCase;
    private final Logger logger = LoggerFactory.getLogger(WriteArticleController.class);

    public WriteArticleController(WriteArticleUseCase writeArticleUseCase) {
        this.writeArticleUseCase = writeArticleUseCase;
    }


    @GetMapping("/article/form")
    public String routeArticleForm() {
        return "/article/form";
    }


    @PostMapping("/articles")
    public String createArticle(@ModelAttribute CreateArticleRequestDto createArticleRequestDto) {
        WriteArticleDto writeArticleDto = new WriteArticleDto(
            createArticleRequestDto.getTitle(),
            createArticleRequestDto.getContent());

        ArticleId articleId = writeArticleUseCase.write(writeArticleDto);
        logger.info("[Log] article is created {}", articleId.getValue());

        return "redirect:/";
    }
}
