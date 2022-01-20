package com.kakao.cafe.interfaces.article;

import com.kakao.cafe.application.article.FindArticleService;
import com.kakao.cafe.application.article.UpdateArticleService;
import com.kakao.cafe.application.article.WriteArticleService;
import com.kakao.cafe.application.article.validation.NonExistsArticleIdException;
import com.kakao.cafe.application.user.FindUserService;
import com.kakao.cafe.application.user.exception.NonExistsUserIdException;
import com.kakao.cafe.application.user.exception.UnauthorizedUserException;
import com.kakao.cafe.domain.article.Article;
import com.kakao.cafe.domain.article.ArticleVo;
import com.kakao.cafe.domain.user.User;
import com.kakao.cafe.interfaces.article.dto.ArticleMapper;
import com.kakao.cafe.interfaces.article.dto.request.UpdateArticleRequestDto;
import com.kakao.cafe.interfaces.article.dto.request.WriteArticleRequestDto;
import com.kakao.cafe.interfaces.article.dto.response.ArticleListResponseDto;
import com.kakao.cafe.interfaces.article.dto.response.ArticleResponseDto;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@Controller
public class ArticleController {

    private final FindArticleService findArticleService;
    private final WriteArticleService writeArticleService;
    private final FindUserService findUserService;
    private final UpdateArticleService updateArticleService;

    public ArticleController(FindArticleService findArticleService, WriteArticleService writeArticleService, FindUserService findUserService, UpdateArticleService updateArticleService) {
        this.findArticleService = findArticleService;
        this.writeArticleService = writeArticleService;
        this.findUserService = findUserService;
        this.updateArticleService = updateArticleService;
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
    public ModelAndView readArticleByIndex(@PathVariable int index, ModelAndView modelAndView) throws NonExistsArticleIdException {
        Article article = findArticleService.findById(index);
        ArticleResponseDto articleResponseDto = ArticleMapper.convertEntityToResponseDto(article);

        modelAndView.addObject("article", articleResponseDto);
        modelAndView.setViewName("qna/show");
        return modelAndView;
    }

    @GetMapping("/question/form")
    public ModelAndView createArticleForm(HttpServletRequest request, ModelAndView modelAndView) {
        User user = (User) request.getAttribute("user");

        modelAndView.addObject("name", user.getName());
        modelAndView.setViewName("/qna/form");
        return modelAndView;
    }

    @GetMapping("/question/{index}/form")
    public ModelAndView updateArticleForm(@PathVariable int index, HttpServletRequest request, ModelAndView modelAndView) throws UnauthorizedUserException {
        User user = (User) request.getAttribute("user");
        Article article = findArticleService.findById(index);

        if (!article.getWriter().equals(user)) {
            throw new UnauthorizedUserException();
        }

        ArticleResponseDto articleResponseDto = ArticleMapper.convertEntityToResponseDto(article);
        modelAndView.addObject("article", articleResponseDto);
        modelAndView.setViewName("/qna/updateForm");
        return modelAndView;
    }

    @PostMapping("/questions")
    public ModelAndView createArticle(@Valid WriteArticleRequestDto writeArticleRequestDto, HttpServletRequest request, ModelAndView modelAndView) throws NonExistsUserIdException {
        User user = (User) request.getAttribute("user");
        ArticleVo articleVo = ArticleMapper.convertWriteArticleDtoToVo(writeArticleRequestDto, user);
        writeArticleService.write(articleVo);

        modelAndView.setViewName("redirect:/");
        return modelAndView;
    }

    @PostMapping("/questions/update")
    public ModelAndView updateArticle(@Valid UpdateArticleRequestDto updateArticleRequestDto, HttpServletRequest request, ModelAndView modelAndView) {
        User user = (User) request.getAttribute("user");

        if (!user.getName().equals(updateArticleRequestDto.getWriter())) {
            throw new UnauthorizedUserException();
        }

        ArticleVo articleVo = ArticleMapper.convertUpdateArticleDtoToVo(updateArticleRequestDto, user);

        updateArticleService.update(articleVo);

        modelAndView.setViewName("redirect:/articles/" + updateArticleRequestDto.getId());
        return modelAndView;
    }

}
