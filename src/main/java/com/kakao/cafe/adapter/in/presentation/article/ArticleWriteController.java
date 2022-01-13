package com.kakao.cafe.adapter.in.presentation.article;

import com.kakao.cafe.application.article.dto.WriteRequest;
import com.kakao.cafe.application.article.port.in.WriteArticleUseCase;
import com.kakao.cafe.view.ErrorMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class ArticleWriteController {

    private static final Logger log = LoggerFactory.getLogger(ArticleWriteController.class);

    private final WriteArticleUseCase writeArticleUseCase;

    public ArticleWriteController(WriteArticleUseCase writeArticleUseCase) {
        this.writeArticleUseCase = writeArticleUseCase;
    }

    @PostMapping("/articles")
    public String register(WriteRequest writeRequest, RedirectAttributes redirectAttributes) {
        try {
            log.info("{} wrote an article", writeRequest.getWriter());
            writeArticleUseCase.writeArticle(writeRequest);
            return "redirect:/";
        } catch (Exception e) {
            log.info("{}", e.getMessage());
            String message = ErrorMessage.getErrorMessage(e);
            redirectAttributes.addAttribute("message", message);
            return "redirect:/errors";
        }
    }
}
