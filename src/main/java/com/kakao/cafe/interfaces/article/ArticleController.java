package com.kakao.cafe.interfaces.article;

import com.kakao.cafe.application.ArticleService;
import com.kakao.cafe.domain.article.Article;
import com.kakao.cafe.domain.article.ArticleVo;
import com.kakao.cafe.interfaces.article.dto.ArticleMapper;
import com.kakao.cafe.interfaces.article.dto.response.ArticleListResponseDto;
import com.kakao.cafe.interfaces.article.dto.response.ArticleResponseDto;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class ArticleController {

    private final ArticleService articleService;

    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @GetMapping("/")
    public ModelAndView readAllArticles(ModelAndView modelAndView) {
        List<Article> articles = articleService.findAll();
        List<ArticleListResponseDto> articleResponseDtoList = ArticleMapper.convertEntityListToResponseDtoList(articles);

        modelAndView.addObject("articles", articleResponseDtoList);
        modelAndView.setViewName("index");

        return modelAndView;
    }

    @GetMapping("/articles/{index}")
    public ModelAndView readArticleByIndex(@PathVariable int index, ModelAndView modelAndView) {
        Article article = articleService.findById(index);
        ArticleResponseDto articleResponseDto = ArticleMapper.convertEntityToResponseDto(article);

        modelAndView.addObject("article", articleResponseDto);
        modelAndView.setViewName("qna/show");

        return modelAndView;
    }


    @PostMapping("/questions")
    public ModelAndView createArticle(ArticleVo articleVo, ModelAndView modelAndView) {
        articleService.write(articleVo);
        modelAndView.setViewName("redirect:/");

        return modelAndView;
    }

}
