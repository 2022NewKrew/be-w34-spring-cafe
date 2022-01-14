package com.kakao.cafe.interfaces.article;

import com.kakao.cafe.application.article.FindArticleService;
import com.kakao.cafe.application.article.WriteArticleService;
import com.kakao.cafe.domain.article.Article;
import com.kakao.cafe.domain.article.ArticleVo;
import com.kakao.cafe.interfaces.article.dto.ArticleMapper;
import com.kakao.cafe.interfaces.article.dto.request.WriteArticleRequestDto;
import com.kakao.cafe.interfaces.article.dto.response.ArticleListResponseDto;
import com.kakao.cafe.interfaces.article.dto.response.ArticleResponseDto;
import com.kakao.cafe.interfaces.article.validation.NonExistsArticleIndexException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

@Controller
public class ArticleController {

    private final FindArticleService findArticleService;
    private final WriteArticleService writeArticleService;

    public ArticleController(FindArticleService findArticleService, WriteArticleService writeArticleService) {
        this.findArticleService = findArticleService;
        this.writeArticleService = writeArticleService;
    }

    @GetMapping("/")
    public ModelAndView readAllArticles(ModelAndView modelAndView) {
        List<Article> articles = findArticleService.findAll();
        List<ArticleListResponseDto> articleResponseDtoList = ArticleMapper.convertEntityListToResponseDtoList(articles);

        modelAndView.addObject("articles", articleResponseDtoList);
        modelAndView.setViewName("index");

        return modelAndView;
    }

    @GetMapping("/articles/{index}")
    public ModelAndView readArticleByIndex(@PathVariable int index, ModelAndView modelAndView) {
        try {
            Article article = findArticleService.findById(index);
            ArticleResponseDto articleResponseDto = ArticleMapper.convertEntityToResponseDto(article);

            modelAndView.addObject("article", articleResponseDto);
            modelAndView.setViewName("qna/show");
        } catch (IllegalArgumentException e) {
            throw new NonExistsArticleIndexException();
        }

        return modelAndView;
    }

    @PostMapping("/questions")
    public ModelAndView createArticle(@Valid WriteArticleRequestDto writeArticleRequestDto, ModelAndView modelAndView) {
        ArticleVo articleVo = ArticleMapper.convertWriteArticleDtoToVo(writeArticleRequestDto);

        writeArticleService.write(articleVo);

        modelAndView.setViewName("redirect:/");
        return modelAndView;
    }

}
