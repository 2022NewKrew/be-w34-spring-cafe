package com.kakao.cafe.service;

import com.kakao.cafe.dto.ArticleDto;
import com.kakao.cafe.dto.ArticleListDto;
import com.kakao.cafe.dto.ArticleRequestDto;
import com.kakao.cafe.entity.Article;
import com.kakao.cafe.entity.User;
import com.kakao.cafe.repository.ArticleRepository;
import com.kakao.cafe.repository.UserRepository;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ArticleService {
    private final ArticleRepository articleRepository;
    private final UserRepository userRepository;

    public ArticleService(ArticleRepository articleRepository, UserRepository userRepository) {
        this.articleRepository = articleRepository;
        this.userRepository = userRepository;
    }

    public void createArticle(ArticleRequestDto articleRequestDto, HttpSession httpSession) {
        User sessionedUser = (User) httpSession.getAttribute("sessionedUser");
        Article article = new Article(articleRequestDto, sessionedUser);
        articleRepository.save(article);
    }

    public List<ArticleListDto> getArticleList() {
        return articleRepository.findAll()
                        .stream()
                        .map(ArticleListDto::entityToDto)
                        .collect(Collectors.toList());
    }

    public ArticleDto findById(String index) {
        return ArticleDto.entityToDto(articleRepository.findById(index));
    }
}
