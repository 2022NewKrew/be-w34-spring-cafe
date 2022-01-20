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
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/article")
public class WriteArticleController {

    private final WriteArticleUseCase writeArticleUseCase;
    private final Logger logger = LoggerFactory.getLogger(WriteArticleController.class);

    public WriteArticleController(WriteArticleUseCase writeArticleUseCase) {
        this.writeArticleUseCase = writeArticleUseCase;
    }


    @GetMapping("/form")
    public String routeArticleForm() {
        return "/article/form";
    }


    @PostMapping()
    public String writeArticle(@ModelAttribute WriteArticleRequestDto writeArticleRequestDto) {
        WriteArticleDto writeArticleDto = new WriteArticleDto(
            writeArticleRequestDto.getTitle(),
            writeArticleRequestDto.getContent());
        ArticleId articleId = writeArticleUseCase.write(writeArticleDto);
        logger.info("[Log] article {} is created", articleId.getValue());
        
        return "redirect:/";
    }
}
