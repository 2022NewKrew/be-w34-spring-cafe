package com.kakao.cafe.service;

import com.kakao.cafe.dto.ArticleDto;
import com.kakao.cafe.dto.ArticleRequestDto;
import com.kakao.cafe.entity.Article;
import com.kakao.cafe.entity.User;
import com.kakao.cafe.repository.ArticleRepository;
import com.kakao.cafe.repository.UserRepository;
import org.springframework.stereotype.Service;

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

    public void createArticle(ArticleRequestDto articleRequestDto) {
        // TODO:  로그인 기능 구현 이후에 articleDto로부터 유저 정보 가져올 수 있어야
        User user = userRepository.findById(articleRequestDto.toString());
        Article article = new Article(articleRequestDto, user);
        articleRepository.save(article);
    }

    public List<ArticleDto> getArticleList() {
        return articleRepository.findAll()
                        .stream()
                        .map(ArticleDto::entityToDto)
                        .collect(Collectors.toList());
    }

    public ArticleDto findById(String index) {
        return ArticleDto.entityToDto(articleRepository.findById(index));
    }
}
