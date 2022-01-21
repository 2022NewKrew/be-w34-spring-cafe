package com.kakao.cafe.controller;

import com.kakao.cafe.controller.interceptor.LoginRequired;
import com.kakao.cafe.dto.ArticleRequestDTO;
import com.kakao.cafe.dto.ArticleResponseDTO;
import com.kakao.cafe.dto.UserResponseDTO;
import com.kakao.cafe.error.exception.AuthorizationException;
import com.kakao.cafe.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

import static com.kakao.cafe.Constant.SESSION_USER;

@Controller
@RequiredArgsConstructor
public class ArticleController {
    private final ArticleService articleService;
    private final Logger logger = LoggerFactory.getLogger(ArticleController.class);

    @LoginRequired
    @GetMapping("/articles/form")
    public String createArticle() {
        return "article/form";
    }

    @GetMapping()
    public String getArticleList(Model model) {
        logger.info("index test");
        List<ArticleResponseDTO> articles = articleService.readAll();
        if(!articles.isEmpty()) {
            logger.info("getArticleList: {}, {}", articles.get(0).getId(), articles.get(0).getAuthor());
        }
        model.addAttribute("articles", articles);
        return "index";
    }

    @LoginRequired
    @GetMapping("/articles/{id}")
    public String getArticle(@PathVariable Long id, Model model) {
        ArticleResponseDTO article = articleService.read(id);
        model.addAttribute("article", article);
        return "article/show";
    }

    @LoginRequired
    @GetMapping("/articles/{id}/form")
    public String updateArticleForm(@PathVariable Long id, @Validated ArticleRequestDTO articleRequestDTO, HttpSession session, Model model) {
        UserResponseDTO user = (UserResponseDTO) session.getAttribute(SESSION_USER);
        if(!user.getName().equals(articleRequestDTO.getAuthor())) {
            throw new AuthorizationException();
        }
        model.addAttribute("article", articleRequestDTO);
        return "article/updateForm";
    }

    @LoginRequired
    @PutMapping("/articles/{id}")
    public String updateArticle(@PathVariable Long id, @Validated ArticleRequestDTO articleRequestDto, Model model) {
        articleService.update(id, articleRequestDto);
        return "redirect:/articles/{id}";
    }

    @LoginRequired
    @DeleteMapping("/articles/{id}")
    public String deleteArticle(@PathVariable Long id, @Validated ArticleRequestDTO articleRequestDTO, HttpSession session) {
        UserResponseDTO user = (UserResponseDTO) session.getAttribute(SESSION_USER);
        if(!user.getName().equals(articleRequestDTO.getAuthor())) {
            throw new AuthorizationException();
        }
        articleService.delete(id);
        return "redirect:/";
    }

    @LoginRequired
    @PostMapping("/articles")
    public String createArticle(@Validated ArticleRequestDTO articleRequestDto) {
        articleService.create(articleRequestDto);
        return "redirect:/";
    }
}
